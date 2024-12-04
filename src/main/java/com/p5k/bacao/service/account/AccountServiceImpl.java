package com.p5k.bacao.service.account;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p5k.bacao.dto.account.AccountDto;
import com.p5k.bacao.entity.account.AccountEntity;
import com.p5k.bacao.mapper.account.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements IAccountService {

    @Override
    public AccountDto fetchAccountByUserName(String userName) {
        AccountDto accountDto = baseMapper.fetchAccountByUserName(userName);
        return accountDto;
    }
}
