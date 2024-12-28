package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;
import com.p5k.bacao.socket.service.room.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.UUID;

import static com.p5k.bacao.socket.core.enums.SendEvent.CREATE_ROOM_SUCCESS;
import static com.p5k.bacao.socket.core.enums.SendEvent.UPDATE_LOBBY;

@Service
public class CreateRoomHandler extends BaseHandler<CreateRoomPayload> {

    private final RoomService roomService;

    protected CreateRoomHandler(RoomService roomService) {
        super(ListenEvent.CREATE_ROOM);
        this.roomService = roomService;
    }

    @Override
    public void onValidPayload(CreateRoomPayload createRoomPayload, Validator validator) {
        createRoomPayload.setRoomId(UUID.randomUUID().toString().replaceAll("-", ""));
        super.onValidPayload(createRoomPayload, validator);
    }

    @Override
    public void onData(SocketIOClient client, CreateRoomPayload createRoomPayload, AckRequest ackRequest, String userId, BroadcastOperations roomBroadcast) {
        RoomDto roomDto = roomService.createRoom(createRoomPayload, userId, client.getSessionId().toString().replace("-", ""));
        client.joinRoom(roomDto.getRoomName());
        client.sendEvent(CREATE_ROOM_SUCCESS.getMessage(), roomDto);
        client.getNamespace().getBroadcastOperations().sendEvent(UPDATE_LOBBY.getMessage(), roomDto);
        
    }

}
