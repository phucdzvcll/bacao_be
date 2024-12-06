package com.p5k.bacao.service.user;

import com.p5k.bacao.entity.account.AccountEntity;
import com.p5k.bacao.mapper.account.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  IUserService {

    private final AccountMapper accountMapper;

    @Override
    public AccountEntity getAccountByUserName(String username) {
        return accountMapper.fetchAccountByUserName(username);
    }
}
