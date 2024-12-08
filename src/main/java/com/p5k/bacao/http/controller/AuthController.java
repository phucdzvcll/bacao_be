package com.p5k.bacao.http.controller;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.constants.BaseConstant;
import com.p5k.bacao.http.core.model.TokenModel;
import com.p5k.bacao.http.core.security.AuthProvider;
import com.p5k.bacao.http.core.security.jwt.JwtService;
import com.p5k.bacao.http.core.util.ResponseUtil;
import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.payload.account.AuthPayload;
import com.p5k.bacao.http.service.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthProvider authProvider;
    //    private final UserService userService;
    private final IAccountService iAccountService;

    private final JwtService jwtService;

    @GetMapping("/")
    public String greet() {
        return "Hello Wold";
    }

//    @CrossOrigin
    @PostMapping("/sign-in")
    public ResultObject<Object> login(@RequestBody AuthPayload authPayload) {
        Authentication authentication = authProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authPayload.getUsername(), authPayload.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AccountEntity userDetails = (AccountEntity) authentication.getPrincipal();

        TokenModel tokenRes =
                jwtService.createToken(userDetails.getId(), BaseConstant.USER_TYPE_NORMAL);

        return ResponseUtil.success(tokenRes);

    }

    @PostMapping("/sign-up")
    public ResultObject<Object> signUp(@RequestBody AuthPayload authPayload) {
        AccountEntity resource = iAccountService.saveAccount(authPayload);
        return ResponseUtil.success(resource);

    }

}
