package com.p5k.bacao.controller;

import com.p5k.bacao.dto.account.AccountDto;
import com.p5k.bacao.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppController {

    private final UserService userService;

    @GetMapping("/")
    public String greet() {
        return "Hello Wold";
    }

    @PostMapping("/login")
    public String login(AccountDto accountDto) {
        return userService.verify(accountDto);
    }

}
