package com.p5k.bacao.http.payload.accountInfo;

import com.p5k.bacao.http.payload.account.UpdatePasswordPayload;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
public class UpdateAccountInfoPayload {
    private String displayName;
    private String userId;
    private String email;
    private String dob;
    private String phoneNumber;
    private UpdatePasswordPayload updatePasswordPayload;
}