package com.p5k.bacao.core.exception;

public class CommunicationException extends ServiceException {

    private static final int DEFAULT_STATUS_CODE = 500;

    public CommunicationException(final String message) {
        this(DEFAULT_STATUS_CODE, message);
    }

    public CommunicationException(final int status, final String message) {
        this(status, message, null);
    }

    public CommunicationException(final String message, final Throwable cause) {
        this(DEFAULT_STATUS_CODE, message, cause);
    }

    public CommunicationException(final int status, final String message, final Throwable cause) {
        super(status, message, cause);
    }

}