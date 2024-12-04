/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.core.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
@JsonPropertyOrder({"status", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ResultObject<T> {

    private ResultCdEnum resultCd; // Message code
    private String resultMsg; // Message response
    private Integer resultStatCd; // restful response status code
    private T data;
    private Meta meta;

    @Data
    @SuperBuilder
    public static class Meta {
        private Long currentPage; // Current page
        private Long pageSize; // Page size
        private Long totalPages; // Total page
        private Long totalRows; // Total data's rows
        private Long totalRecs;
    }

    public enum ResultCdEnum {
        SUCCESS, FAILURE
    }

    @Getter
    public enum HttpStatusCodeEnum {
        OK(HttpStatus.OK, "HttpResponseStatus.OK"),
        CREATED(HttpStatus.CREATED, "HttpResponseStatus.Created"),
        BAD_REQUEST(HttpStatus.BAD_REQUEST, "HttpResponseStatus.BadRequest"),
        UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "HttpResponseStatus.Unauthorized"),
        FORBIDDEN(HttpStatus.FORBIDDEN, "HttpResponseStatus.Forbidden"),
        NOT_FOUND(HttpStatus.NOT_FOUND, "HttpResponseStatus.NotFound"),
        METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "HttpResponseStatus.MethodNotAllowed"),
        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "HttpResponseStatus.InternalServerError"),
        NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "HttpResponseStatus.NotImplemented"),
        SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "HttpResponseStatus.ServiceUnavailable");

        private final HttpStatus httpStatusCd;
        private final String defaultMessage;

        HttpStatusCodeEnum(HttpStatus httpStatusCd, String httpsMessageCd) {
            this.httpStatusCd = httpStatusCd;

            this.defaultMessage = httpsMessageCd;
        }

        public static HttpStatusCodeEnum getByCode(int code) {
            for (HttpStatusCodeEnum item : HttpStatusCodeEnum.values()) {
                if (code == item.getHttpStatusCd().value())
                    return item;
            }
            return null;
        }

    }
}