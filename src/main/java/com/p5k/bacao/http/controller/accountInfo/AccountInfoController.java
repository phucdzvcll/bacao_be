package com.p5k.bacao.http.controller.accountInfo;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.security.CustomSecurityContextHolder;
import com.p5k.bacao.http.core.util.ResponseUtil;
import com.p5k.bacao.http.dto.accountInfo.AccountDetailDto;
import com.p5k.bacao.http.module.AccountModule;
import com.p5k.bacao.http.payload.accountInfo.UpdateAccountInfoPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("acc/")
@RequiredArgsConstructor
@CrossOrigin
public class AccountInfoController {

    private final AccountModule accountModule;

    @PatchMapping(value = "update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultObject<AccountDetailDto> updateAccountInfo(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "displayName", required = false) String displayName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "dob", required = false) String dob,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar
    ) {
        UpdateAccountInfoPayload updateAccountInfoPayload = new UpdateAccountInfoPayload();
        updateAccountInfoPayload.setUserId(userId);
        updateAccountInfoPayload.setDisplayName(displayName);
        updateAccountInfoPayload.setEmail(email);
        updateAccountInfoPayload.setDob(dob);
        updateAccountInfoPayload.setPhoneNumber(phoneNumber);
        AccountDetailDto accountDetailDto = accountModule.updateAccount(updateAccountInfoPayload, avatar);
        return ResponseUtil.success(accountDetailDto);
    }

    @GetMapping("detail")
    public ResultObject<AccountDetailDto> getUserInfo() {
        AccountDetailDto accountDetailDto = accountModule.getAccountDetail(CustomSecurityContextHolder.getUserId());
        return ResponseUtil.success(accountDetailDto);
    }
}
