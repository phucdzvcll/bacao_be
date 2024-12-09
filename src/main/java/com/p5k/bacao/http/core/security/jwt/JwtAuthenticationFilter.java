package com.p5k.bacao.http.core.security.jwt;

import com.p5k.bacao.http.core.constants.JWTConstant;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.security.CustomSecurityContextHolder;
import com.p5k.bacao.http.core.util.WebUtils;
import com.p5k.bacao.http.core.xtools.XChecker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JWTConstant.HEADER_STRING);
        String authToken;
        if (header != null && header.startsWith(JWTConstant.TOKEN_PREFIX) && SecurityContextHolder.getContext().getAuthentication() == null) {
            authToken = header.replace(JWTConstant.TOKEN_PREFIX, "");
            try {
                if (Boolean.TRUE.equals(jwtUtil.isTokenExpired(authToken))) {
                    handleTokenExpired(response);
                    return;
                }
                Claims claims = jwtUtil.getClaimsFromToken(authToken);
                if (XChecker.isNotNull(claims)) {
                    CustomSecurityContextHolder.setCustomerContext(claims);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            claims,
                            null,
                            CustomSecurityContextHolder.getAuthorities().stream().map(
                                    SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (IllegalArgumentException e) {
                log.error("an error occurred during getting username from token", e);
            } catch (ExpiredJwtException e) {
                log.warn("the token is expired and not valid anymore", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        try {
            filterChain.doFilter(request, response);

        } finally {
            CustomSecurityContextHolder.clear();
        }
    }

    private void handleTokenExpired(HttpServletResponse response) throws IOException {

        String str = "{" +
                "\"status\":" + "{" +
                "\"code\":" + HttpStatus.FORBIDDEN.value() +
                ",\"success\":" + Boolean.FALSE +
                ",\"message\":\"" + WebUtils.getMessage(
                ServiceCodeEnum.AUTH_EXCEPTION_TOKEN_CANNOT_CREATE.getMessage()) +
                "\"" +
                "}" +
                "}";

        response.setContentType("application/json;charset=\"UTF-8\"");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(str);
    }
}
