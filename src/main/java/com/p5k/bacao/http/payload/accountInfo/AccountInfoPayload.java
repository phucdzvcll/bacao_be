package com.p5k.bacao.http.payload.accountInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class AccountInfoPayload {
    private String displayName;
    private String email;
    private int age;
    private String phoneNumber;
}