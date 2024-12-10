package com.p5k.bacao.http.controller.accountInfo;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.util.ResponseUtil;
import com.p5k.bacao.http.dto.accountInfo.AccountInfoDto;
import com.p5k.bacao.http.module.AccountModule;
import com.p5k.bacao.http.payload.accountInfo.UpdateAccountInfoPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("acc/")
@RequiredArgsConstructor
public class AccountInfoController {

    private final AccountModule accountModule;

    @PatchMapping("update")
    public ResultObject<AccountInfoDto> updateAccountInfo(@RequestBody UpdateAccountInfoPayload updateAccountInfoPayload) {
        AccountInfoDto accountInfoDto = accountModule.updateAccount(updateAccountInfoPayload);
        return ResponseUtil.success(accountInfoDto);
    }

    @GetMapping("detail")
    public ResultObject<AccountInfoDto> getUserInfo() {
        AccountInfoDto accountInfoDto = accountModule.getUserInfo();
        return ResponseUtil.success(accountInfoDto);
    }
}
