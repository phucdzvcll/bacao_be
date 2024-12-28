package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.enums.SendEvent;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.payload.room.LeaveRoomPayload;
import com.p5k.bacao.socket.service.room.RoomService;
import org.springframework.stereotype.Service;

@Service
public class LeaveRoomHandler extends BaseHandler<LeaveRoomPayload> {

    private final RoomService roomService;

    protected LeaveRoomHandler(RoomService roomService) {
        super(ListenEvent.LEAVE_ROOM);
        this.roomService = roomService;
    }

    @Override
    public void onData(SocketIOClient client, LeaveRoomPayload leaveRoomPayload, AckRequest ackRequest, String userId, BroadcastOperations roomBroadcast) {
        RoomDto roomDto = roomService.leaveRoom(leaveRoomPayload.getRoomId(), userId, client.getSessionId().toString().replace("-", ""));
        //leave room
        client.leaveRoom(roomDto.getRoomName());
        //update lobby
        client.getNamespace().getBroadcastOperations().sendEvent(SendEvent.UPDATE_LOBBY.getMessage(), roomDto);
        //update room
        roomBroadcast.sendEvent(SendEvent.CLIENT_LEAVED_ROOM.getMessage(), userId);

    }
}
