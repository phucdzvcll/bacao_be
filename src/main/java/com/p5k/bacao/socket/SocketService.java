package com.p5k.bacao.socket;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final SocketIOServer socketIOServer;


    @PreDestroy
    public void destroy() {
        System.out.println("Socket.IO server stopped...");
        socketIOServer.stop();
    }

    public void start() {
        socketIOServer.start();
        System.out.println("Socket.IO server started...");
    }

    public void stop() {
        socketIOServer.stop();
        System.out.println("Socket.IO server stopped...");
    }
}
