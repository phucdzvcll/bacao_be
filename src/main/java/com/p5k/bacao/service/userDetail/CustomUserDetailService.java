package com.p5k.bacao.service.userDetail;

import com.p5k.bacao.dto.account.AccountDto;
import com.p5k.bacao.entity.userForAuth.CustomUserDetail;
import com.p5k.bacao.service.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto account = accountService.fetchAccountByUserName(username);
        return new CustomUserDetail(account);
    }
}
