package com.p5k.bacao.socket.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.p5k.bacao.socket.handlers.room.CreateRoomHandler;
import com.p5k.bacao.socket.payload.CreateRoomPayload;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.p5k.bacao.socket.handlers.room.CreateRoomHandler.eventName;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocketService {

    private final SocketIOServer socketIOServer;
    private final CreateRoomHandler createRoomHandler;

    @PreDestroy
    public void destroy() {
        socketIOServer.stop();
    }

    public void start() {
        socketIOServer.addEventListener(eventName, CreateRoomPayload.class, createRoomHandler);
        socketIOServer.start();
        log.info("Socket.IO server started...");
    }
}
