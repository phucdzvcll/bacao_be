package com.p5k.bacao.http.service.accountInfo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.p5k.bacao.http.dto.accountInfo.AccountInfoDto;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;


public interface IAccountInfoService extends IService<AccountInfoEntity> {

    AccountInfoDto getAccountInfoDtoByUserId(String userId);

    AccountInfoEntity getAccountInfoByUserId(String userId);

}
