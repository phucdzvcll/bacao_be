package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.p5k.bacao.socket.payload.CreateRoomPayload;
import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.args.SetArgs;
import io.github.dengliming.redismodule.redisjson.utils.GsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateRoomHandler implements DataListener<CreateRoomPayload> {

    private final RedisJSON redisJSON;

    public static String redisKey = "room";
    public static String eventName = "createRoom";


    @Override
    public void onData(SocketIOClient socketIOClient, CreateRoomPayload createRoomPayload, AckRequest ackRequest) {
        redisJSON.set(redisKey, SetArgs.Builder.create(".", GsonUtils.toJson(createRoomPayload)));
        socketIOClient.joinRoom(createRoomPayload.getRoomName());
    }
}
