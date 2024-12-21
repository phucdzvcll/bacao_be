package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.enums.SendEvent;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.service.room.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JoinToLobbyEvent extends BaseHandler<Object> {
    private final RoomService roomService;

    protected JoinToLobbyEvent(RoomService roomService) {
        super(ListenEvent.JOIN_TO_LOBBY);
        this.roomService = roomService;
    }

    @Override
    public void onData(SocketIOClient client, Object o, AckRequest ackRequest, String userId) {
        List<Object> rooms = roomService.getAllRooms();
        client.sendEvent(SendEvent.JOIN_TO_ROOM_LOBBY_SUCCESS.getMessage(), rooms);
    }
}
