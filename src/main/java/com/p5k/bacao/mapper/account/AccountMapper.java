package com.p5k.bacao.mapper.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.p5k.bacao.entity.account.AccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper extends BaseMapper<AccountEntity> {
    AccountEntity fetchAccountByUserName(@Param("username") String userName);
}
