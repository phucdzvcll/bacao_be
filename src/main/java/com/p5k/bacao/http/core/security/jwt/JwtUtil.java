package com.p5k.bacao.http.core.security.jwt;

import com.p5k.bacao.http.core.xtools.XChecker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {
    @Value("${application.jwt.auth}")
    private String jwtAuth;

    @Value("${application.jwt.jwt_expiration}")
    private long jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        String jwtSecret = "1eIxOqQ9vb1kPJmho685DNn6dq4FQF6guuTdYMztvqb2KGoiaKmOddg5OV6sp8KZK5irm8yVz2uoEtfeLjdaMA3yknYCevjHcWghcXiijPGCn4Rr41iox6gOqEOFTL3o";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String doGenerateToken(String userName, Map<String, Object> map) {
        ClaimsBuilder claims = Jwts.claims().subject(userName);

        try {
            if (!XChecker.isNull(map) && !map.isEmpty()) {
                claims.add(map);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return Jwts.builder()
                .claims(claims.build())
                .issuer(jwtAuth)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key)
                .compact();
    }

    public Boolean isTokenExpired(String authToken) {
        final Date expiration = getExpirationDateFromToken(authToken);
        if (XChecker.isNull(expiration)) {
            return true;
        }
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
