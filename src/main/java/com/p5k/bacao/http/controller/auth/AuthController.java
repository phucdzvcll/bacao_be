package com.p5k.bacao.http.controller.auth;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.constants.BaseConstant;
import com.p5k.bacao.http.core.model.TokenModel;
import com.p5k.bacao.http.core.security.AuthProvider;
import com.p5k.bacao.http.core.security.jwt.JwtService;
import com.p5k.bacao.http.core.util.ResponseUtil;
import com.p5k.bacao.http.dto.accountInfo.AccountDetailDto;
import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.module.AccountModule;
import com.p5k.bacao.http.payload.account.CreateAccountPayload;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthProvider authProvider;
    private final AccountModule accountModule;

    private final JwtService jwtService;

    @GetMapping("/test")
    public String greet() {
        return "Hello Wold";
    }

    //    @CrossOrigin
    @PostMapping("/sign-in")
    public ResultObject<Object> login(@RequestBody CreateAccountPayload createAccountPayload) {
        Authentication authentication = authProvider.authenticate(
                new UsernamePasswordAuthenticationToken(createAccountPayload.getUsername(), createAccountPayload.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AccountEntity userDetails = (AccountEntity) authentication.getPrincipal();

        TokenModel tokenRes =
                jwtService.createToken(userDetails.getId(), BaseConstant.USER_TYPE_NORMAL);
        AccountDetailDto accountDetailDto = accountModule.getAccountDetail();
        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenRes);
        result.put("user", accountDetailDto);
        return ResponseUtil.success(result);

    }

    @PostMapping("/sign-up")
    public ResultObject<AccountDetailDto> signUp(@RequestBody CreateAccountPayload createAccountPayload) {
        return ResponseUtil.success(accountModule.createAccount(createAccountPayload));

    }

}
