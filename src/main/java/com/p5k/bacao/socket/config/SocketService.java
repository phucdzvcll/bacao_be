package com.p5k.bacao.socket.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.handlers.room.*;
import com.p5k.bacao.socket.payload.room.LeaveRoomPayload;
import com.p5k.bacao.socket.payload.room.ChangeSeatPayload;
import com.p5k.bacao.socket.payload.room.ClientReadyPayload;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;
import com.p5k.bacao.socket.payload.room.JoinRoomPayload;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SocketService {

    private final SocketIOServer socketIOServer;
    private final CreateRoomEvent createRoomEvent;
    private final JoinToLobbyEvent joinToLobbyEvent;
    private final JoinToRoomEvent joinToRoomEvent;
    private final LeaveRoomHandler leaveRoomHandler;
    private final DisconnectHandler disconnectHandler;
    private final ChangeSeatHandler changeSeatHandler;
    private final ClientReadyHandler clientReadyHandler;

    @PreDestroy
    public void destroy() {
        socketIOServer.stop();
    }

    public void start() {
        socketIOServer.addEventListener(createRoomEvent.getEventName(), CreateRoomPayload.class, createRoomEvent);
        socketIOServer.addEventListener(ListenEvent.JOIN_TO_LOBBY.getMessage(), Object.class, joinToLobbyEvent);
        socketIOServer.addEventListener(joinToRoomEvent.getEventName(), JoinRoomPayload.class, joinToRoomEvent);
        socketIOServer.addEventListener(leaveRoomHandler.getEventName(), LeaveRoomPayload.class, leaveRoomHandler);
        socketIOServer.addEventListener(changeSeatHandler.getEventName(), ChangeSeatPayload.class, changeSeatHandler);
        socketIOServer.addEventListener(clientReadyHandler.getEventName(), ClientReadyPayload.class, clientReadyHandler);
        socketIOServer.addDisconnectListener(disconnectHandler);

        socketIOServer.start();
        log.info("Socket.IO server started...");
    }
}
