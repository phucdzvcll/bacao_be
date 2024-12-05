package com.p5k.bacao.core.base;

import lombok.Getter;

@Getter
public class GenericDto<D> {
    private D dto;
    public GenericDto(){}
    public GenericDto(D dto){ this.dto = dto; }
    public void setDto(D dto) { this.dto = dto; }
}
