package com.p5k.bacao.http.dto.accountInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailDto {

    private String userId;

    private String userName;

    private String firstLogin;

    private String displayName;

    private String email;

    private String dob;

    private String phoneNumber;

    private String avatar;

}
