package com.p5k.bacao.http.service.accountInfo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.p5k.bacao.http.dto.accountInfo.AccountDetailDto;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;

import java.util.List;


public interface IAccountInfoService extends IService<AccountInfoEntity> {

    AccountDetailDto getAccountDetailByUserId(String userId);

    List<AccountDetailDto> getAccountDetailByUserId(List<String> userIds);

    AccountInfoEntity getAccountInfoByUserId(String userId);

    String getAvatar(String userId);

}
