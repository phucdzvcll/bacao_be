package com.p5k.bacao.http.payload.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UpdatePasswordPayload {
    private String newPassword;
    private String oldPassword;
}
