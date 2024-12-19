package com.p5k.bacao.socket.service.room;

import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.dto.room.UserInRoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;
import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.args.GetArgs;
import java.util.List;
import java.util.concurrent.CompletionStage;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JacksonCodec;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends RoomService {

  static final String roomPrefix = "room:";

  private final RedisJSON redisJSON;
  private final RedissonClient redisson;

  @Override
  public List<RoomDto> getAllRooms() {
//        String queryPath = "$.[*]";
//        return objectMapper.convertValue(
//                redisJSON.get("room", Object.class, new GetArgs().path(queryPath)),
//                new TypeReference<>() {
//                });
    return List.of();

  }

  @Override
  public RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId, String clientId) {

    XChecker.isTrueThruMsg(exitByRoomName(createRoomPayload.getRoomName()),
        ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_ALREADY_EXITS);

    RoomDto roomDto = new RoomDto();
    roomDto.setRoomName(createRoomPayload.getRoomName());
    roomDto.setPassword(createRoomPayload.getPassword());
    roomDto.setAdminId(userId);
    roomDto.setAdminClientId(clientId);
    UserInRoomDto e1 = new UserInRoomDto();
    e1.setUserId(userId);
    e1.setSkSessionId(clientId);
    roomDto.setUserIds(List.of(e1));
    //
    //    Gson gson = new Gson();
    //
    //    redisJSON.set(roomPrefix + createRoomPayload.getRoomName(),
    //        SetArgs.Builder.create(".", gson.toJson(roomDto)));

    RBucket<RoomDto> buget = redisson.getJsonBucket(roomPrefix + createRoomPayload.getRoomName(),
        new JacksonCodec<>(RoomDto.class));
    buget.set(roomDto);

//        SearchResult searchResult = jedisPooled.ftSearch("idx_rooms", "@roomName:" + createRoomPayload.getRoomName());
//
//        if(XChecker.isNotEmpty(searchResult.getDocuments())){
//            RoomDto roomDto1 = new Gson().fromJson(searchResult.getDocuments().get(0).fe, RoomDto.class);
//        }
    return roomDto;

//        String queryPath = "$.[?(@.roomName=='" + createRoomPayload.getRoomName() + "')].roomId";
//        Object roomId = redisJSON.get("room", List.class, new GetArgs().path(queryPath));
//
//        if (XChecker.isEmpty(roomId)) {
//            createRoomPayload.setAdminId(userId);
//            UserInRoomPayload userInRoomPayload = new UserInRoomPayload();
//            userInRoomPayload.setSkSessionId(clientId);
//            userInRoomPayload.setUserId(userId);
//            createRoomPayload.setUserIds(List.of(userInRoomPayload));
//            redisJSON.arrAppend("room", ".", createRoomPayload);
//            return objectMapper.convertValue(createRoomPayload, RoomDto.class);
//        } else {
//            throw new ServiceException(ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_ALREADY_EXITS);
//        }
  }

  @Override
  public RoomDto findRoomById(String roomId) {
//        String queryPath = "$.[?(@.roomId=='" + roomId + "')]";
//        Object room = redisJSON.get("room", Object.class, new GetArgs().path(queryPath));
//        List<RoomDto> rooms = objectMapper.convertValue(room, new TypeReference<>() {
//        });
//        if (rooms.isEmpty()) {
//            throw new ServiceException(ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_NOT_FOUND);
//        }
//        return rooms.get(0);
    return new RoomDto();
  }

  @Override
  public Boolean exitByRoomName(String roomName) {
    RoomDto roomDto = redisJSON.get(roomPrefix + roomName, RoomDto.class, new GetArgs());
    return XChecker.isNotNull(roomDto);
  }

  @Override
  public List<RoomDto> findRoomByUserId(String userId) {
    return List.of();
  }

  @Override
  public CompletionStage<Long> joinToRoom(String roomId, String userId, String clientId) {
//        UserInRoomPayload userInRoomPayload = new UserInRoomPayload();
//        userInRoomPayload.setSkSessionId(clientId);
//        userInRoomPayload.setUserId(userId);
//        RoomDto roomDto = findRoomById(roomId);
//        if (roomDto.getUserIds().size() > 17) {
//            throw new ServiceException(ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_FULL);
//        }
//
//        return redisJSON.arrAppendAsync("room", "$.[?(@.roomId=='" + roomId + "')].userIds", userInRoomPayload);
    return null;
  }
}
