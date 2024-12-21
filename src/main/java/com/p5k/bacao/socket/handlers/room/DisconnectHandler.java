package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.p5k.bacao.socket.core.enums.SendEvent;
import com.p5k.bacao.socket.dto.room.RoomDto;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RJsonBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JacksonCodec;
import org.springframework.stereotype.Service;

import static com.p5k.bacao.socket.core.enums.SendEvent.CLIENT_LEAVED_ROOM;

@Service
@RequiredArgsConstructor
public class DisconnectHandler implements DisconnectListener {

    private final RedissonClient redissonClient;

    @Override
    public void onDisconnect(SocketIOClient client) {
        String userId = client.get("userId");
        Iterable<String> keys = redissonClient.getKeys().getKeys();
        JacksonCodec<RoomDto> jsonCodec = new JacksonCodec<>(RoomDto.class);
        SocketIONamespace namespace = client.getNamespace();
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        keys.forEach(key -> {
            RJsonBucket<RoomDto> jsonBucket = redissonClient.getJsonBucket(key, jsonCodec);
            RoomDto roomDto = jsonBucket.get();

            roomDto.getUserIds().removeIf(userInRoomDto -> userInRoomDto.getUserId().equals(userId));
            if (!roomDto.getUserIds().isEmpty()) {
                BroadcastOperations roomOperations = namespace.getRoomOperations(roomDto.getRoomName());
                roomOperations.sendEvent(CLIENT_LEAVED_ROOM.getMessage(), userId);
                jsonBucket.set(roomDto);
            } else {
                jsonBucket.delete();
            }
            client.leaveRoom(roomDto.getRoomName());
            broadcastOperations.sendEvent(SendEvent.UPDATE_LOBBY.getMessage(), roomDto);
        });
    }
}
