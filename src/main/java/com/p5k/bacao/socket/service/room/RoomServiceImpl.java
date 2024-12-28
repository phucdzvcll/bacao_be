package com.p5k.bacao.socket.service.room;

import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.socket.core.enums.UserStateEnum;
import com.p5k.bacao.socket.dto.room.ClientReadiedDto;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.dto.room.UserInRoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;
import com.p5k.bacao.socket.service.RoomDtoCodec;
import io.github.dengliming.redismodule.redisjson.RedisJSON;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RJsonBucket;
import org.redisson.api.RSearch;
import org.redisson.api.RedissonClient;
import org.redisson.api.search.query.QueryOptions;
import org.redisson.api.search.query.SearchResult;
import org.redisson.codec.JacksonCodec;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static com.p5k.bacao.socket.core.enums.RedisIndex.ROOM_BY_NAME;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends RoomService {

    static final String roomPrefix = "room:";

    private final RedisJSON redisJSON;
    private final RedissonClient redisson;

    JacksonCodec<RoomDto> roomDtoCodec = new JacksonCodec<>(RoomDto.class);

    @Override
    public List<Object> getAllRooms() {
        RSearch rSearch = redisson.getSearch(new RoomDtoCodec());
        SearchResult r = rSearch.search(ROOM_BY_NAME.getIndex(), "*", QueryOptions.defaults()
                .returnAttributes());

        Stream<Object> stream = r.getDocuments().stream().map(
                document -> document.getAttributes().get("$"));
        return stream.toList();


    }

    @Override
    public RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId, String clientId) {

        XChecker.isTrueThruMsg(XChecker.isNotNull(findRoomName(createRoomPayload.getRoomName())),
                ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_ALREADY_EXITS);

        RoomDto roomDto = new RoomDto();
        roomDto.setRoomName(createRoomPayload.getRoomName());
        roomDto.setPassword(createRoomPayload.getPassword());
        roomDto.setAdminId(userId);
        roomDto.setAdminClientId(clientId);
        roomDto.setUserIds(List.of());
        RBucket<RoomDto> buget = redisson.getJsonBucket(roomPrefix + roomDto.getRoomId(),
                roomDtoCodec);

        buget.set(roomDto);

        return buget.get();
    }

    @Override
    public RoomDto findRoomById(String roomId) {
        RBucket<RoomDto> buget = redisson.getJsonBucket(roomPrefix + roomId,
                roomDtoCodec);
        RoomDto roomDto = buget.get();
        XChecker.isNullThruMsg(roomDto, ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_NOT_FOUND);
        return roomDto;
    }


    @Override
    public RoomDto findRoomName(String roomName) {
        RSearch rSearch = redisson.getSearch(new RoomDtoCodec());
        SearchResult r = rSearch.search(ROOM_BY_NAME.getIndex(), "@roomName:" + roomName,
                QueryOptions.defaults()
                        .returnAttributes());
        if (r.getDocuments().isEmpty()) {
            return null;
        }
        return (RoomDto) r.getDocuments().get(0).getAttributes().get("$");
    }

    @Override
    public CompletionStage<Long> joinToRoom(String roomId, String userId, String clientId) {
        UserInRoomDto userInRoom = new UserInRoomDto();
        userInRoom.setSkSessionId(clientId);
        userInRoom.setUserId(userId);
        RoomDto roomDto = findRoomById(roomId);
        userInRoom.setSeatNum(findSmallestMissingSeatNum(roomDto.getUserIds()));
        return redisJSON.arrAppendAsync(roomPrefix + roomId, "$.userIds", userInRoom);

    }

    public static int findSmallestMissingSeatNum(List<UserInRoomDto> users) {
        Set<Integer> existingSeats = new HashSet<>();
        for (UserInRoomDto user : users) {
            existingSeats.add(user.getSeatNum());
        }

        int seatNum = 0;
        while (existingSeats.contains(seatNum)) {
            seatNum++;
        }
        return seatNum;
    }

    public static UserInRoomDto findUserBySeat(List<UserInRoomDto> users, int seatNum) {
        for (UserInRoomDto user : users) {
            if (user.getSeatNum() == seatNum) {
                return user;
            }
        }
        return null;
    }

    @Override
    public RoomDto leaveRoom(String roomId, String userId, String clientId) {
        RJsonBucket<RoomDto> roomDtoRJsonBucket = redisson.getJsonBucket(roomPrefix + roomId, roomDtoCodec);
        RoomDto roomDto = roomDtoRJsonBucket
                .get();
        roomDto.getUserIds().removeIf(userInRoomDto -> userInRoomDto.getUserId().equals(userId));
        if (roomDto.getUserIds().isEmpty()) {
            roomDtoRJsonBucket.delete();
        }
        return roomDto;
    }

    @Override
    public void changeSeatService(String roomId, String userId, int seatNum) {
        RoomDto roomDto = findRoomById(roomId);
        UserInRoomDto userInRoomDto = findUserBySeat(roomDto.getUserIds(), seatNum);
        XChecker.isTrueThruMsg(userInRoomDto != null, "Seat is have an user");

        roomDto.getUserIds().forEach(userInRoomDto1 -> {
            if (userInRoomDto1.getUserId().equals(userId)) {
                userInRoomDto1.setSeatNum(seatNum);
            }
        });

        RBucket<RoomDto> buget = redisson.getJsonBucket(roomPrefix + roomId,
                roomDtoCodec);
        buget.set(roomDto);
    }

    @Override
    public ClientReadiedDto clientReady(String roomId, String userId) {
        RoomDto roomDto = findRoomById(roomId);
        roomDto.getUserIds().forEach(userInRoomDto1 -> {
            if (userInRoomDto1.getUserId().equals(userId)) {
                userInRoomDto1.setUserStateEnum(UserStateEnum.READIED);
            }
        });
        RBucket<RoomDto> buget = redisson.getJsonBucket(roomPrefix + roomId,
                roomDtoCodec);
        buget.set(roomDto);
        ClientReadiedDto clientReadiedDto = new ClientReadiedDto();
        clientReadiedDto.setUserId(userId);
        return clientReadiedDto;
    }
}
