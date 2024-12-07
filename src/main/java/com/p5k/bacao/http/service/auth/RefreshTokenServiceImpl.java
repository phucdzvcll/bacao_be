package com.p5k.bacao.http.service.auth;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p5k.bacao.http.entity.auth.RefreshTokenEntity;
import com.p5k.bacao.http.mapper.auth.RefreshTokenMapper;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshTokenEntity> implements IRefreshTokenService {
}
