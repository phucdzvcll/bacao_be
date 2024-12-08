package com.p5k.bacao.http.core.exception;

import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import lombok.Getter;
import lombok.Setter;

public class SocketException extends RuntimeException {

    @Setter
    @Getter
    private ServiceCodeEnum responseCodeEnum;
    @Setter
    @Getter
    private int code;
    @Getter
    private String message;
    private Object[] params;

    @Setter
    @Getter
    private SocketIOClient client;



    public SocketException(ServiceCodeEnum errorCode, String msg, SocketIOClient client) {
        super(msg);
        this.client = client;
        this.responseCodeEnum = errorCode;
        this.message = msg;
    }

    public SocketException(String msg, SocketIOClient client) {
        super(msg);
        this.client = client;
        this.message = msg;
    }

    public SocketException(int code, String msg, Throwable cause, SocketIOClient client) {
        super(msg, cause);
        this.client = client;
        this.code = code;
    }

    public SocketException(int code, String msg, SocketIOClient client) {
        super(msg);
        this.client = client;
        this.code = code;
        this.message = msg;
    }

    public SocketException(ServiceCodeEnum errorCode, SocketIOClient client) {
        this.responseCodeEnum = errorCode;
    }


    public Object[] getParams() {
        if (this.params == null) {
            return new Object[]{};
        }
        return params;
    }

}