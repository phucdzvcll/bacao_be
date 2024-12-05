package com.p5k.bacao.service.account;

import com.baomidou.mybatisplus.extension.service.IService;
import com.p5k.bacao.entity.account.AccountEntity;
import com.p5k.bacao.payload.account.SignUpPayload;


public interface IAccountService extends IService<AccountEntity> {

//    AccountEntity fetchAccountByUserName(String userName);

    AccountEntity saveAccount(SignUpPayload signUpPayload);
}
