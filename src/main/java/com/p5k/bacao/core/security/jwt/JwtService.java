package com.p5k.bacao.core.security.jwt;

import com.p5k.bacao.core.model.TokenModel;
import com.p5k.bacao.entity.auth.RefreshTokenEntity;
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

    @Value("${application.jwt.jwt_expiration}")
    private long jwtRefreshExp;

    private final JwtUtil jwtUtil;

    public TokenModel createToken(String userId, String username) {
        RefreshTokenEntity ref = new RefreshTokenEntity();
        ref.setUserId(userId);
        ref.setExpiryDate(Instant.now().plusMillis(jwtRefreshExp));
        ref.setToken(UUID.randomUUID().toString());

        // Set userId to Claims
        Map<String, Object> mapToken = new HashMap<>();
        mapToken.put("id", userId);


        String token = jwtUtil.doGenerateToken(username, mapToken);
//        if (!refreshTokenService.save(ref)) {
//            throw new ServiceException(ServiceCodeEnum.AUTH_EXCEPTION_TOKEN_CANNOT_CREATE); // Can not create token!
//        }
        return new TokenModel(token, ref.getToken());
    }

}
