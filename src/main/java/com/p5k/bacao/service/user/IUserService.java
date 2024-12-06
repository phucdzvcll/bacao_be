package com.p5k.bacao.service.user;

import com.p5k.bacao.entity.account.AccountEntity;

public interface IUserService {
    AccountEntity getAccountByUserName(String username);
}
