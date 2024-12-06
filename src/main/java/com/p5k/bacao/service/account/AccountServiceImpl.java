package com.p5k.bacao.service.account;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p5k.bacao.core.enums.ServiceCodeEnum;
import com.p5k.bacao.core.exception.ServiceException;
import com.p5k.bacao.entity.account.AccountEntity;
import com.p5k.bacao.mapper.account.AccountMapper;
import com.p5k.bacao.payload.account.AuthPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements IAccountService {
    private final PasswordEncoder passwordEncoder;
//    private final Validator validator;

//    @Override
//    public AccountEntity fetchAccountByUserName(String userName) {
//        return baseMapper.fetchAccountByUserName(userName);
//    }

    @Override
    public AccountEntity saveAccount(AuthPayload signUpPayload) {
        try {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setUsername(signUpPayload.getUsername());
            String encode = passwordEncoder.encode(signUpPayload.getPassword());
            accountEntity.setPassword(encode);
            save(accountEntity);
            return accountEntity;
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ServiceCodeEnum.USER_EXCEPTION_USER_NAME_DUPLICATED);
        }
    }
}
