/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.http.core.exception.template;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
@Component
public class ResponseFactoryImpl implements IResponseFactory {

    @Override
    public ResultObject<Object> produce(Exception e) {
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        if (e instanceof ServiceException serviceException) {
            code = serviceException.getCode();
        }
        ResultObject.Status status = new ResultObject.Status();
        status.setCode(code);
        status.setSuccess(false);
        status.setMessage(e.getMessage());

        ResultObject<Object> dto = new ResultObject<>();
        dto.setStatus(status);
        return dto;
    }

    @Override
    public ResultObject<Object> produce(Integer code, String message) {
        ResultObject.Status status = new ResultObject.Status();
        status.setCode(code);
        status.setSuccess(false);
        status.setMessage(message);

        ResultObject<Object> dto = new ResultObject<>();
        dto.setStatus(status);
        return dto;
    }
}
