package com.p5k.bacao.core.security.jwt;

import com.p5k.bacao.core.xtools.XChecker;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${application.jwt.auth}")
    private String jwtAuth;

    @Value("${application.jwt.secretKey}")
    private String jwtSecret;

    @Value("${application.jwt.jwt_expiration}")
    private long jwtExpiration;

    public String doGenerateToken(String userName, Map<String, Object> map) {
        ClaimsBuilder claims = Jwts.claims().subject(userName);

        try {
            if (!XChecker.isNull(map) && !map.isEmpty()) {
                claims.add(map);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(claims.build())
                .issuer(jwtAuth)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key)
                .compact();
    }

}
