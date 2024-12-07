package com.p5k.bacao.http.service.account;

import com.baomidou.mybatisplus.extension.service.IService;
import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.payload.account.AuthPayload;


public interface IAccountService extends IService<AccountEntity> {

//    AccountEntity fetchAccountByUserName(String userName);

    AccountEntity saveAccount(AuthPayload authPayload);
}
