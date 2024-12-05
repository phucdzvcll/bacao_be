package com.p5k.bacao.core.exception;

import com.p5k.bacao.core.enums.ServiceCodeEnum;
import lombok.Getter;
import lombok.Setter;

public class ServiceException extends RuntimeException {

    @Setter
    @Getter
    private ServiceCodeEnum responseCodeEnum;
    @Setter
    @Getter
    private int code;
    @Getter
    private String message;
    private Object[] params;

    public ServiceException(ServiceCodeEnum errorCode, String msg) {
        super(msg);
        this.responseCodeEnum = errorCode;
        this.message = msg;
    }

    public ServiceException(String msg) {
        super(msg);
        this.message = msg;
    }

    public ServiceException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public ServiceException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public ServiceException(ServiceCodeEnum errorCode) {
        this.responseCodeEnum = errorCode;
    }

    public ServiceException(ServiceCodeEnum errorCode, String messageCode, Object... params) {
        this(errorCode, messageCode);
        this.message = messageCode;
        this.responseCodeEnum = errorCode;
        this.params = params;
    }

    public Object[] getParams() {
        if (this.params == null) {
            return new Object[]{};
        }
        return params;
    }

}