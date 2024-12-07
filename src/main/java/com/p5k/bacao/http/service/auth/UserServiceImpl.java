package com.p5k.bacao.http.service.auth;

import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.mapper.account.AccountMapper;
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
