package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisconnectHandler implements DisconnectListener {

    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {

    }
}
