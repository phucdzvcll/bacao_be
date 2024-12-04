//package com.p5k.bacao.service;
//
//import com.p5k.bacao.dto.account.AccountDto;
//import com.p5k.bacao.entity.account.AccountEntity;
//import com.p5k.bacao.service.account.IAccountService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UserService {
//
//    @Autowired
//    private JWTService jwtService;
//
////    @Autowired
////    private IAccountService accountService;
//
//    @Autowired
//    private AuthenticationManager authManager;
//
//
//
////    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//
//
//    public String verify(AccountDto user) {
//        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(user.getUsername());
//        } else {
//            return "fail";
//        }
//    }
//}