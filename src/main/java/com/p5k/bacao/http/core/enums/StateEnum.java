package com.p5k.bacao.http.core.enums;

import com.p5k.bacao.http.core.constants.BaseConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateEnum {
    Y(BaseConstant.KEY_YES),
    N(BaseConstant.KEY_NO);
    private final String value;
}
