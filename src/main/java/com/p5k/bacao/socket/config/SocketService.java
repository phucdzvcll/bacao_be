package com.p5k.bacao.socket.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.p5k.bacao.socket.handlers.room.CreateRoomEvent;
import com.p5k.bacao.socket.handlers.room.JoinToLobbyEvent;
import com.p5k.bacao.socket.payload.CreateRoomPayload;
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

    @PreDestroy
    public void destroy() {
        socketIOServer.stop();
    }

    public void start() {
        socketIOServer.addEventListener(createRoomEvent.getEventName(),CreateRoomPayload.class, createRoomEvent);
        socketIOServer.addEventListener(joinToLobbyEvent.getEventName(),Object.class, joinToLobbyEvent);

        socketIOServer.addConnectListener(client ->
                log.info("Client connected: {}", client.getSessionId())
        );

        socketIOServer.start();
        log.info("Socket.IO server started...");
    }
}
