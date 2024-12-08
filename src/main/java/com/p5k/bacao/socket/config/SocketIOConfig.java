package com.p5k.bacao.socket.config;

import com.corundumstudio.socketio.AuthorizationResult;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.p5k.bacao.http.core.constants.JWTConstant;
import com.p5k.bacao.http.core.security.jwt.JwtUtil;
import com.p5k.bacao.socket.handlers.exception.SocketExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Slf4j
@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
public class SocketIOConfig {

    @Value("${spring.socket.port}")
    private int port;
    @Value("${spring.socket.host}")
    private String host;

    private final JwtUtil jwtUtil;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = getConfiguration();
        config.setExceptionListener(new SocketExceptionHandler());
        config.setAuthorizationListener((HandshakeData handshakeData) -> {
            String header = handshakeData.getHttpHeaders().get(JWTConstant.HEADER_STRING);
            if (header != null && header.startsWith(JWTConstant.TOKEN_PREFIX)) {
                String authToken = header.replace(JWTConstant.TOKEN_PREFIX, "");
                try {
                    Boolean isTokenExpired = jwtUtil.isTokenExpired(authToken);
                    return new AuthorizationResult(!isTokenExpired);
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
