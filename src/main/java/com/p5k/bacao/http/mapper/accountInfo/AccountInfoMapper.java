package com.p5k.bacao.http.mapper.accountInfo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.p5k.bacao.http.dto.accountInfo.AccountDetailDto;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfoEntity> {
    AccountDetailDto getAccountInfoByUserId(@Param("userId") String userId);

    List<AccountDetailDto> getAccountInfoByUserIds(@Param("userIds") List<String> userId);

}
