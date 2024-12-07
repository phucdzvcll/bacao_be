package com.p5k.bacao.http.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.p5k.bacao.http.entity.auth.RefreshTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper extends BaseMapper<RefreshTokenEntity> {
}
