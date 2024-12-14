package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.enums.SendEvent;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.RoomDto;
import com.p5k.bacao.socket.payload.CreateRoomPayload;
import com.p5k.bacao.socket.service.room.RoomService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Getter
@Slf4j
@Component
public class CreateRoomEvent extends BaseHandler<CreateRoomPayload> {

    private final RoomService roomService;

    protected CreateRoomEvent(RoomService roomService) {
        super(ListenEvent.CREATE_ROOM);
        this.roomService = roomService;
    }

    @Override
    public void onData(SocketIOClient client, CreateRoomPayload createRoomPayload, AckRequest ackRequest, String userId) {
        RoomDto roomDto = roomService.createRoom(createRoomPayload, userId);
        client.joinRoom(roomDto.getRoomId());
        client.sendEvent(SendEvent.CREATE_ROOM_SUCCESS.getMessage(), roomDto);
    }

}
