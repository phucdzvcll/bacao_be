/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.core.exception.template;


import com.p5k.bacao.core.base.ResultObject;

/**
 * <p>
 * This class produces the exception factory.
 * </p>
 *
 * @author TranVanThiep
 * @created 2023-Jul-07
 */
public interface IResponseFactory {

    ResultObject produce(Exception e);

    ResultObject produce(Integer code, String message);

}
