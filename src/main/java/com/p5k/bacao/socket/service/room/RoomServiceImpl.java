package com.p5k.bacao.socket.service.room;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p5k.bacao.http.core.exception.ServiceException;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;
import com.p5k.bacao.socket.payload.room.UserInRoomPayload;
import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.args.GetArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends RoomService {
    private final RedisJSON redisJSON;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<RoomDto> getAllRooms() {
        String queryPath = "$.[*]";
        return objectMapper.convertValue(
                redisJSON.get("room", Object.class, new GetArgs().path(queryPath)),
                new TypeReference<>() {
                });

    }

    @Override
    public RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId, String clientId) {
        String queryPath = "$.[?(@.roomName=='" + createRoomPayload.getRoomName() + "')].roomId";
        Object roomId = redisJSON.get("room", List.class, new GetArgs().path(queryPath));

        if (XChecker.isEmpty(roomId)) {
            createRoomPayload.setAdminId(userId);
            UserInRoomPayload userInRoomPayload = new UserInRoomPayload();
            userInRoomPayload.setSkSessionId(clientId);
            userInRoomPayload.setUserId(userId);
            createRoomPayload.setUserIds(List.of(userInRoomPayload));
            redisJSON.arrAppend("room", ".", createRoomPayload);
            return objectMapper.convertValue(createRoomPayload, RoomDto.class);
        } else {
            throw new ServiceException("Room already exists");
        }
    }

    @Override
    public RoomDto findRoomById(String roomId) {
        String queryPath = "$.[?(@.roomId=='" + roomId + "')]";
        Object room = redisJSON.get("room", Object.class, new GetArgs().path(queryPath));
        List<RoomDto> rooms = objectMapper.convertValue(room, new TypeReference<>() {
        });
        if (rooms.isEmpty()) {
            return null;
        }
        return rooms.get(0);
    }

    @Override
    public RoomDto joinToRoom(String roomId, String userId, String clientId) {


        UserInRoomPayload userInRoomPayload = new UserInRoomPayload();
        userInRoomPayload.setSkSessionId(clientId);
        userInRoomPayload.setUserId(userId);
        redisJSON.arrAppendAsync("room", "$.[?(@.roomId=='" + roomId + "')].userIds", userInRoomPayload);
        return findRoomById(roomId);
    }
}
