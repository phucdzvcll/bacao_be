package com.p5k.bacao.http.module;

import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;
import com.p5k.bacao.http.payload.account.AuthPayload;
import com.p5k.bacao.http.service.account.IAccountService;
import com.p5k.bacao.http.service.accountInfo.IAccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountModule {
    private final IAccountService accountService;
    private final IAccountInfoService accountInfoService;

    @Transactional(rollbackFor = Exception.class)
    public AccountEntity createAccount(AuthPayload authPayload){
        AccountEntity accountEntity = accountService.saveAccount(authPayload);
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setUserId(accountEntity.getId());
        accountInfoService.save(accountInfoEntity);
        return accountEntity;
    }


}
