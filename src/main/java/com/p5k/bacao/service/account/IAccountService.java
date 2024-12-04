package com.p5k.bacao.service.account;

import com.baomidou.mybatisplus.extension.service.IService;
import com.p5k.bacao.dto.account.AccountDto;
import com.p5k.bacao.entity.account.AccountEntity;
import org.springframework.stereotype.Component;


public interface IAccountService extends IService<AccountEntity> {

    public AccountDto fetchAccountByUserName(String userName);

}
