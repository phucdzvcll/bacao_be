package com.p5k.bacao.http.core.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericDto<D> {
    private D dto;
    public GenericDto(){}
    public GenericDto(D dto){ this.dto = dto; }
}
