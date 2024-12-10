package com.p5k.bacao.http.mapper.accountInfo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.p5k.bacao.http.dto.accountInfo.AccountInfoDto;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfoEntity> {
    AccountInfoDto getAccountInfoByUserId(@Param("userId") String userId);

}
