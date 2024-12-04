package com.p5k.bacao.controller;

import com.p5k.bacao.core.base.ResultObject;
import com.p5k.bacao.entity.account.AccountEntity;
import com.p5k.bacao.payload.account.SignUpPayload;
import com.p5k.bacao.service.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppController {

//    private final UserService userService;
    private final IAccountService iAccountService;

    @GetMapping("/")
    public String greet() {
        return "Hello Wold";
    }

//    @PostMapping("/sign-in")
//    public String login(AccountDto accountDto) {
//        return userService.verify(accountDto);
//    }

    @PostMapping("/sign-up")
    public ResultObject<Object> signUp(@RequestBody SignUpPayload accountDto) {
        AccountEntity resource = iAccountService.saveAccount(accountDto);
        return ResultObject.builder().data(resource).build();

    }

}
