package com.p5k.bacao.http.service.accountInfo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p5k.bacao.http.dto.accountInfo.AccountInfoDto;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;
import com.p5k.bacao.http.mapper.accountInfo.AccountInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfoEntity> implements IAccountInfoService {

    @Override
    public AccountInfoDto getAccountInfoDtoByUserId(String userId) {
        return baseMapper.getAccountInfoByUserId(userId);
    }

    @Override
    public AccountInfoEntity getAccountInfoByUserId(String userId) {
        return this.lambdaQuery().eq(AccountInfoEntity::getUserId, userId).one();
    }
}
