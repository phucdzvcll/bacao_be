package com.p5k.bacao.http.core.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class BaseDto implements Serializable {
    @JsonProperty(access = Access.READ_ONLY)
    private String id;
    @JsonIgnore
    private LocalDateTime systime;
    @JsonProperty(access = Access.READ_ONLY)
    private String createdBy;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(defaultValue = "N")
    private String delFlag;
}
