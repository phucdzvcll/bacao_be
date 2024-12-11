package com.p5k.bacao.http.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  TokenModel {

    private String accessToken;
    private String refreshToken;

}
