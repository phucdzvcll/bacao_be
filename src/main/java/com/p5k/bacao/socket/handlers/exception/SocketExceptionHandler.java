package com.p5k.bacao.socket.handlers.exception;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SocketExceptionHandler implements ExceptionListener {
    @Override
    public void onEventException(Exception e, List<Object> list, SocketIOClient socketIOClient) {
        log.error("SocketExceptionHandler onEventException", e);

    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient socketIOClient) {
        log.error("SocketExceptionHandler onDisconnectException", e);

    }

    @Override
    public void onConnectException(Exception e, SocketIOClient socketIOClient) {
        log.error("SocketExceptionHandler onConnectException", e);
    }

    @Override
    public void onPingException(Exception e, SocketIOClient socketIOClient) {
        log.error("SocketExceptionHandler onPingException", e);
    }

    @Override
    public void onPongException(Exception e, SocketIOClient socketIOClient) {
        log.error("SocketExceptionHandler onPongException", e);
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        return false;
    }

    @Override
    public void onAuthException(Throwable throwable, SocketIOClient socketIOClient) {
        log.error("SocketExceptionHandler onAuthException", throwable);
    }
}
