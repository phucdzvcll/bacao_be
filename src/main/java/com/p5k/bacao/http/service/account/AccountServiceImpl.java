package com.p5k.bacao.http.service.account;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.mapper.account.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements IAccountService { }
