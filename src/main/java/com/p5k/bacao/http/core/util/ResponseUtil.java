package com.p5k.bacao.http.core.util;/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */


import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.xtools.XChecker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 * The REST response structure.
 * </p>
 *
 * @updateBy TranVanThiep
 * @updateAt 2023-Jul-07
 */
public class ResponseUtil {

    public static <T> ResultObject<T> success(String message) {
        ResultObject<T> result = new ResultObject<>();
        ResultObject.Status status = new ResultObject.Status();
        status.setSuccess(true);
        status.setCode(HttpStatus.OK.value());
        status.setMessage(message);
        result.setStatus(status);
        return result;
    }

    public static <T> ResultObject<T> success() {
        return success(HttpStatus.OK.name());
    }

    public static <T> ResultObject<T> success(String message, T data) {
        ResultObject<T> result = success(message);
        result.setData(data);
        return result;
    }

    public static <T> ResultObject<T> success(T data) {
        return success(HttpStatus.OK.name(), data);
    }

    public static ResponseEntity<Void> responseVoid() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static <T> ResultObject<List<T>> successList(List<T> contents, long totalPages
            , long totalItem, long currentPage, long pageSize, String message) {
        ResultObject.Meta metaData = new ResultObject.Meta();
        metaData.setTotalPages(totalPages);
        metaData.setTotalRows(totalItem);
        metaData.setPageSize(pageSize);
        metaData.setCurrentPage(currentPage);

        ResultObject.Status status = new ResultObject.Status();
        status.setSuccess(true);
        status.setCode(HttpStatus.OK.value());
        status.setMessage(message);

        ResultObject<List<T>> result = new ResultObject<>();
        result.setStatus(status);
        result.setData(contents);
        result.setMeta(metaData);
        return result;
    }

    public static <T> ResultObject<List<T>> successList(
            List<T> contents,
            long totalPages,
            long totalItem,
            long currentPage,
            long pageSize
    ) {
        return successList(contents, totalPages, totalItem, currentPage, pageSize, HttpStatus.OK.name());
    }

    public static <T> ResultObject<List<T>> successList(IPage<T> pageData) {
        ResultObject<List<T>> rtl = successList(
                pageData.getRecords(),
                pageData.getPages(),
                pageData.getTotal(),
                pageData.getCurrent(),
                pageData.getSize(),
                HttpStatus.OK.name()
        );
        return rtl;
    }

    public static <T> ResultObject<T> error(int code, String message) {
        ResultObject<T> result = new ResultObject<>();

        ResultObject.Status status = new ResultObject.Status();
        status.setSuccess(false);
        status.setCode(code);
        status.setMessage(message);

        result.setStatus(status);
        return result;
    }

    public static <T> ResultObject<T> error(String message, T data) {
        ResultObject<T> result = error(HttpStatus.BAD_REQUEST.value(), message);
        result.setData(data);
        return result;
    }

    public static <T> ResultObject<T> error(int code, String message, T data) {
        ResultObject<T> result = error(code, message);
        result.setData(data);
        return result;
    }

    public static <T> ResultObject<T> error(String mess) {
        if (XChecker.isBlank(mess)) {
            mess = ServiceCodeEnum.PROCESSING_FAILED.getMessage();
        }
        return error(HttpStatus.BAD_REQUEST.value(), mess);
    }

    public static <T> ResultObject<T> error() {
        return error(StringUtils.EMPTY);
    }

    public static <T> ResultObject<T> verify(int dataSize) {
        return dataSize > 0 ? success() : error(StringUtils.EMPTY);
    }

    public static <T> ResultObject<T> verify(Object data) {
        return XChecker.isEmpty(data) ? error(StringUtils.EMPTY) : success();
    }
}
