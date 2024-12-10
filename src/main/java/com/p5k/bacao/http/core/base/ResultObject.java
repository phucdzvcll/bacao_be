package com.p5k.bacao.http.core.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({"status", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResultObject<T> {

    private Status status;
    private T data;
    private Meta meta;

    @Data
    public static class Status {
        private String message;
        private Integer code;
        private boolean success;
    }

    @Data
    public static class Meta {
        private Long totalPages;
        private Long pageSize;
        private Long totalRows;
        private Long currentPage;
    }
}