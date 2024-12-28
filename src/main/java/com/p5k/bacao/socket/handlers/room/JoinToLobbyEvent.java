package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.p5k.bacao.socket.core.enums.SendEvent;
import com.p5k.bacao.socket.service.room.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JoinToLobbyEvent implements DataListener<Object> {
    private final RoomService roomService;

    protected JoinToLobbyEvent(RoomService roomService) {

        this.roomService = roomService;
    }

    @Override
    public void onData(SocketIOClient client, Object var2, AckRequest var3) {
        List<Object> rooms = roomService.getAllRooms();
        client.sendEvent(SendEvent.JOIN_TO_ROOM_LOBBY_SUCCESS.getMessage(), rooms);
    }
}
