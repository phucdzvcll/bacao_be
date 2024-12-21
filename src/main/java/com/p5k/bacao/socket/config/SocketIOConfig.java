package com.p5k.bacao.socket.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.corundumstudio.socketio.AuthorizationResult;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.p5k.bacao.http.core.constants.JWTConstant;
import com.p5k.bacao.http.core.security.jwt.JwtUtil;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.service.account.IAccountService;
import com.p5k.bacao.socket.handlers.exception.SocketExceptionHandler;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
public class SocketIOConfig {

    @Value("${spring.socket.port}")
    private int port;
    @Value("${spring.socket.host}")
    private String host;

    private final JwtUtil jwtUtil;

    private final IAccountService accountService;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = getConfiguration();
        config.setExceptionListener(new SocketExceptionHandler());
        config.setAuthorizationListener((HandshakeData handshakeData) -> {
            String header = handshakeData.getSingleUrlParam(JWTConstant.HEADER_STRING);
            if (XChecker.isNotNull(header) && header.startsWith(JWTConstant.TOKEN_PREFIX)) {
                String authToken = header.replace(JWTConstant.TOKEN_PREFIX, "");
                try {
                    Boolean isTokenExpired = jwtUtil.isTokenExpired(authToken);

                    if (!isTokenExpired) {
                        Claims claims = jwtUtil.getClaimsFromToken(authToken);
                        if (XChecker.isNotNull(claims)) {

                            String userId = (String) claims.get("id");
                            boolean isUserExits = accountService.exists(new QueryWrapper<AccountEntity>().lambda().ge(AccountEntity::getId, userId));
                            Map<String, Object> storeParams = new HashMap<>();
                            storeParams.put("userId", userId);
                            return new AuthorizationResult(isUserExits, storeParams);
                        }
                        return new AuthorizationResult(false);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                    return new AuthorizationResult(false);
                }
            }
            return new AuthorizationResult(false);
        });
        return new SocketIOServer(config);
    }

    private Configuration getConfiguration() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        return config;
    }
}
