package com.p5k.bacao.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.p5k.bacao.core.base.BaseDto;
import com.p5k.bacao.core.enums.StateEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto extends BaseDto {
    private String username;
    @JsonIgnore
    private String password;
    @NotNull
    private StateEnum useYn;

}