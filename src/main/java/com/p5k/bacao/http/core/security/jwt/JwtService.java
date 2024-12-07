package com.p5k.bacao.http.core.security.jwt;

import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.exception.ServiceException;
import com.p5k.bacao.http.core.model.TokenModel;
import com.p5k.bacao.http.entity.auth.RefreshTokenEntity;
import com.p5k.bacao.http.service.auth.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${application.jwt.refresh_jwt_expiration}")
    private long jwtRefreshExp;

    private final JwtUtil jwtUtil;

    private final IRefreshTokenService refreshTokenService;
    public TokenModel createToken(String userId, String username) {
        RefreshTokenEntity ref = new RefreshTokenEntity();
        ref.setUserId(userId);
        ref.setExpiryDate(Instant.now().plusMillis(jwtRefreshExp));
        ref.setToken(UUID.randomUUID().toString());

        // Set userId to Claims
        Map<String, Object> mapToken = new HashMap<>();
        mapToken.put("id", userId);


        String token = jwtUtil.doGenerateToken(username, mapToken);
        if (!refreshTokenService.save(ref)) {
            throw new ServiceException(ServiceCodeEnum.AUTH_EXCEPTION_TOKEN_CANNOT_CREATE);
        }
        return new TokenModel(token, ref.getToken());
    }

}
