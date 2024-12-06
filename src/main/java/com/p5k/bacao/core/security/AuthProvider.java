package com.p5k.bacao.core.security;

import com.p5k.bacao.core.enums.ServiceCodeEnum;
import com.p5k.bacao.core.exception.ServiceException;
import com.p5k.bacao.entity.account.AccountEntity;
import com.p5k.bacao.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthProvider(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        AccountEntity accountEntity;
        try {
            accountEntity = userService.getAccountByUserName(username);
        } catch (UsernameNotFoundException e) {
            throw new ServiceException(ServiceCodeEnum.AUTH_EXCEPTION_AUTHENTICATION_FAIL);
        }

        if (accountEntity == null) {
            throw new ServiceException(ServiceCodeEnum.AUTH_EXCEPTION_USERNAME_IS_NOT_FOUND);
        }

        if (!passwordEncoder.matches(password, accountEntity.getPassword())) {
            throw new ServiceException(ServiceCodeEnum.AUTH_EXCEPTION_AUTHENTICATION_FAIL);
        } else {
            return new UsernamePasswordAuthenticationToken(accountEntity, null, accountEntity.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
