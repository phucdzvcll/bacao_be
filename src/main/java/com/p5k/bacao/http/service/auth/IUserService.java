package com.p5k.bacao.http.service.auth;

import com.p5k.bacao.http.entity.account.AccountEntity;

public interface IUserService {
    AccountEntity getAccountByUserName(String username);
}
