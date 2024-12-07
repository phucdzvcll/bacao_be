/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.http.core.exception.template;


import com.p5k.bacao.http.core.base.ResultObject;

public interface IResponseFactory {

    ResultObject<Object> produce(Exception e);

    ResultObject<Object> produce(Integer code, String message);

}
