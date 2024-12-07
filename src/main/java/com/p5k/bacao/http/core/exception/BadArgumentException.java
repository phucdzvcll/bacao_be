package com.p5k.bacao.http.core.exception;

public class BadArgumentException extends ServiceException {
    private static final int STATUS_CODE = 400;

    public BadArgumentException(final String s) {
        super(STATUS_CODE, s);
    }

    public BadArgumentException(final String s, final Throwable cause) {
        super(STATUS_CODE, s, cause);
    }

}