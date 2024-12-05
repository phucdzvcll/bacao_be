/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.core.exception.template;

import com.p5k.bacao.core.base.ResultObject;
import com.p5k.bacao.core.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * <p>
 * The implementation of {@link IResponseFactory}.
 * </p>
 *
 * @author TranVanThiep
 * @created 2023-Jul-07
 */
@Component
public class ResponseFactoryImpl implements IResponseFactory {

    @Override
    public ResultObject produce(Exception e) {
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        if (e instanceof ServiceException serviceException) {
            code = serviceException.getCode();
        }
        ResultObject.Status status = new ResultObject.Status();
        status.setCode(code);
        status.setSuccess(false);
        status.setMessage(e.getMessage());

        ResultObject dto = new ResultObject();
        dto.setStatus(status);
        return dto;
    }

    @Override
    public ResultObject produce(Integer code, String message) {
        ResultObject.Status status = new ResultObject.Status();
        status.setCode(code);
        status.setSuccess(false);
        status.setMessage(message);

        ResultObject dto = new ResultObject();
        dto.setStatus(status);
        return dto;
    }
}
