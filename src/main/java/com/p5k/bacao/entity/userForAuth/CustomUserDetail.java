package com.p5k.bacao.entity.userForAuth;

import com.p5k.bacao.core.constants.BaseConstant;
import com.p5k.bacao.dto.account.AccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomUserDetail implements UserDetails {

    private final AccountDto account;

    public CustomUserDetail(AccountDto account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return true;
//        return Objects.equals(account.getUseYn().getValue(), BaseConstant.KEY_YES);
    }
}
