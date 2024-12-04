///*
// * Copyright (c) 2023 Smartee Vina. All rights reserved.
// *
// */
//
//package com.p5k.bacao.core.util;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.p5k.bacao.core.base.ResultObject;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.Objects;
//
///**
// * <p>
// * The REST response structure.
// * </p>
// *
// * @updateBy TranVanThiep
// * @createAt 2024-Jul-07
// * @updateAt 2024-Apr-08
// * @version 1.0.1
// */
//public class ResponseUtil {
//
//    /**
//     * Response success with  message.
//     *
//     * @param message   Success message.
//     * @return          Response result
//     * @param <T>       Type of response data
//     */
//    public static <T> ResultObject<T> success(String message) {
//        return ResultObject.<T>builder()
//            .resultCd(ResultObject.ResultCdEnum.SUCCESS)
//            .resultMsg(CommonUtils.isEmptyStr(message) ? WebUtils
//                .getMessage(ResultObject.HttpStatusCodeEnum.OK.getDefaultMessage()) : message)
//            .resultStatCd(ResultObject.HttpStatusCodeEnum.OK.getHttpStatusCd().value())
//            .build();
//    }
//
//    /**
//     * Response success.
//     *
//     * @return      Response result
//     * @param <T>   Type of response data
//     */
//    public static <T> ResultObject<T> success() {
//        return success(HttpStatus.OK.name());
//    }
//
//    /**
//     * Response success with data output and message.
//     *
//     * @param message   Success message
//     * @param data      Data output
//     * @return          Response result
//     * @param <T>       Type of response data
//     */
//    public static <T> ResultObject<T> success(String message, T data) {
//        ResultObject<T> result = success(message);
//        result.setData(data);
//        return result;
//    }
//
//    /**
//     * Response success with data output.
//     *
//     * @param data  Data
//     * @return      Response result
//     * @param <T>   Type of response data
//     */
//    public static <T> ResultObject<T> success(T data) {
//        return success(HttpStatus.OK.name(), data);
//    }
//
//    /**
//     * Response with void method.
//     *
//     * @return   Response result
//     */
//    public static ResponseEntity<Void> responseVoid() {
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    /**
//     * Response data with success list.
//     *
//     * @param contents      List data
//     * @param totalPages    Total pages
//     * @param totalItems    Total items
//     * @param currentPage   Current page
//     * @param pageSize      Page size
//     * @param message       Message
//     * @return              Response result
//     * @param <T>           Type of response data
//     */
//    public static <T> ResultObject<T> successList(T contents, long totalPages
//        , long totalItems, long currentPage, long pageSize, String message) {
//        // Metadata for page info.
//        ResultObject.Meta metaData = ResultObject.Meta.builder()
//            .totalPages(totalPages)
//            .totalRows(totalItems)
//            .pageSize(pageSize)
//            .currentPage(currentPage).build();
//
//        return ResultObject.<T>builder()
//            .meta(metaData)
//            .resultCd(ResultCdEnum.SUCCESS)
//            .resultStatCd(HttpStatusCodeEnum.OK.getHttpStatusCd().value())
//            .resultMsg(CommonUtils.isEmptyStr(message) ? WebUtils
//                .getMessage(HttpStatusCodeEnum.OK.getDefaultMessage()) : message)
//            .data(contents).build();
//    }
//
//    /**
//     * Response data with success list.
//     *
//     * @param contents      List data
//     * @param totalPages    Total pages
//     * @param totalItems    Total items
//     * @param currentPage   Current page
//     * @param pageSize      Page size
//     * @return              Response result
//     * @param <T>           Type of response data
//     */
//    public static <T> ResultObject<T> successList(T contents, long totalPages
//        , long totalItems, long currentPage, long pageSize) {
//        return successList(contents, totalPages, totalItems, currentPage, pageSize, HttpStatus.OK.name());
//    }
//
//    /**
//     * Response data with success list.
//     *
//     * @param contents      List data
//     * @param totalPages    Total pages
//     * @param totalItem     Total items
//     * @param currentPage   Current page
//     * @param pageSize      Page size
//     * @param totalRecs     Total records
//     * @return              Response result
//     * @param <T>           Type of response data
//     */
//    public static <T> ResultObject<T> successList(T contents, long totalPages
//        , long totalItem, long currentPage, long pageSize, long totalRecs) {
//        ResultObject<T> rtl = successList(contents, totalPages, totalItem, currentPage, pageSize, HttpStatus.OK.name());
//        rtl.getMeta().setTotalRecs(totalRecs);// total records?
//        return rtl;
//    }
//
//    /**
//     * Response error with error code and error message.
//     *
//     * @param code      Error code
//     * @param message   Error message
//     * @return          Response error result
//     * @param <T>       Type of response data
//     */
//    public static <T> ResultObject<T> error(int code, String message) {
//        return ResultObject.<T>builder()
//            .resultCd(ResultCdEnum.FAILURE)
//            .resultStatCd(code)
//            .resultMsg(CommonUtils.isEmptyStr(message) ? WebUtils
//                .getMessage(Objects.requireNonNull(HttpStatusCodeEnum
//                    .getByCode(code)).getDefaultMessage()) : message).build();
//    }
//
//    /**
//     * Response error with message and error data.
//     *
//     * @param message   Error message
//     * @param data      Error data
//     * @return          Response result
//     * @param <T>       Type of response data
//     */
//    public static <T> ResultObject<T> error(String message, T data) {
//        ResultObject<T> result = error(HttpStatus.BAD_REQUEST.value(), message);
//        result.setData(data);
//        return result;
//    }
//
//    /**
//     * Response error with error code, message, error data
//     *
//     * @param code      Error code
//     * @param message   Message
//     * @param data      Error data
//     * @return          Response error result
//     * @param <T>       Type of response data
//     */
//    public static <T> ResultObject<T> error(int code, String message, T data) {
//        ResultObject<T> result = error(code, message);
//        result.setData(data);
//        return result;
//    }
//
//    /**
//     * Response error with message.
//     *
//     * @param mess  Message content
//     * @return      Response result.
//     * @param <T>   Type of response data
//     */
//    public static <T> ResultObject<T> error(String mess) {
//        if (XChecker.isBlank(mess)) {
//            mess = ServiceCodeEnum.PROCESSING_FAILED.getMessage();
//        }
//        return error(HttpStatus.BAD_REQUEST.value(), mess);
//    }
//
//    /**
//     * Response error with empty content.
//     *
//     * @return      Response result
//     * @param <T>   Type of response data
//     */
//    public static <T> ResultObject<T> error() {
//        return error(StringUtils.EMPTY);
//    }
//
//    /**
//     * Verify data.
//     *
//     * @param dataSize  Data size
//     * @return          Response data.
//     * @param <T>       Type of response data
//     */
//    public static <T> ResultObject<T> verify(int dataSize) {
//        return dataSize > 0 ? success() : error(StringUtils.EMPTY);
//    }
//
//    /**
//     * Verify data.
//     *
//     * @param data  Data output
//     * @return      Response object
//     * @param <T>   Type of response data
//     */
//    public static <T> ResultObject<T> verify(Object data) {
//        return XChecker.isEmpty(data) ? error(StringUtils.EMPTY) : success();
//    }
//
//    /**
//     * Create a response Object with HttpStatus code enum.
//     *
//     * @param data                  Data output
//     * @param httpStatusCodeEnum    Http status code
//     * @param message               Message: if null or empty get default value
//     * @return                      Response result
//     * @param <T>                   Type of response data
//     */
//    public static <T> ResultObject<T> success(T data,
//        HttpStatusCodeEnum httpStatusCodeEnum,
//        String... message) {
//        return ResultObject.<T>builder()
//            .resultCd(ResultCdEnum.SUCCESS)
//            .resultMsg(CommonUtils.isAllStringEmpty(message) ? WebUtils
//                .getMessage(httpStatusCodeEnum.getDefaultMessage()) : String
//                .join(StringUtils.EMPTY, message))
//            .resultStatCd(httpStatusCodeEnum.getHttpStatusCd().value())
//            .data(data)
//            .build();
//    }
//
//    /**
//     * Success with method
//     *
//     * @param data          Data output
//     * @param requestMethod Request method
//     * @param message       Message: if null or empty get default message
//     * @return              Response object
//     * @param <T>           Type of response data
//     */
//    public static <T> ResultObject<T> successMethod(T data,
//        RequestMethod requestMethod,
//        String... message) {
//        return requestMethod == RequestMethod.PUT ?
//            success(data, HttpStatusCodeEnum.CREATED, message)
//            : success(data, HttpStatusCodeEnum.OK, message);
//    }
//
//    /**
//     * Create response entity with page info.
//     *
//     * @param pageInfos Page info
//     * @param message   Message: If null get default value
//     * @return          Response entity
//     * @param <T>       Type of response data
//     */
//    public static <T> ResultObject<Object> successList(IPage<T> pageInfos, String... message) {
//
//        Meta pageInfo = Meta.builder()
//            .totalPages(pageInfos.getPages())
//            .currentPage(pageInfos.getCurrent())
//            .pageSize(pageInfos.getSize())
//            .totalRows(pageInfos.getTotal()).build();
//
//        return ResultObject.builder()
//            .resultCd(ResultCdEnum.SUCCESS)
//            .resultMsg(CommonUtils.isAllStringEmpty(message) ? WebUtils
//                .getMessage(HttpStatusCodeEnum.OK.getDefaultMessage())
//                : String.join(StringUtils.EMPTY, message))
//            .resultStatCd(HttpStatusCodeEnum.OK.getHttpStatusCd().value())
//            .data(pageInfos.getRecords())
//            .meta(pageInfo).build();
//
//    }
//}
