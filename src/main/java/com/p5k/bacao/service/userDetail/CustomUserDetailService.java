package com.p5k.bacao.service.userDetail;

import com.p5k.bacao.dto.account.AccountDto;
import com.p5k.bacao.entity.userForAuth.CustomUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

//    @Autowired
//    private IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AccountDto account = accountService.fetchAccountByUserName(username);
        AccountDto account = new AccountDto();
        account.setUsername("asd");
        account.setPassword("asd");
        return new CustomUserDetail(account);
    }
}
