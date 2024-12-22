package com.p5k.bacao.http.controller.accountInfo;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.security.CustomSecurityContextHolder;
import com.p5k.bacao.http.core.util.ResponseUtil;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.http.dto.accountInfo.AccountDetailDto;
import com.p5k.bacao.http.module.AccountModule;
import com.p5k.bacao.http.payload.account.UpdatePasswordPayload;
import com.p5k.bacao.http.payload.accountInfo.UpdateAccountInfoPayload;
import com.p5k.bacao.http.service.accountInfo.IAccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("acc/")
@RequiredArgsConstructor
@CrossOrigin
public class AccountInfoController {

    private final AccountModule accountModule;
    private final IAccountInfoService accountInfoService;

    @PostMapping(value = "update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultObject<AccountDetailDto> updateAccountInfo(
            @RequestParam(value = "oldPassword", required = false) String oldPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "displayName", required = false) String displayName,
            @RequestParam(value = "dob", required = false) String dob,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar
    ) {
        UpdateAccountInfoPayload updateAccountInfoPayload = new UpdateAccountInfoPayload();
        if (XChecker.isNotEmpty(oldPassword) && XChecker.isNotEmpty(newPassword)) {
            UpdatePasswordPayload updatePasswordPayload = new UpdatePasswordPayload();
            updatePasswordPayload.setOldPassword(oldPassword);
            updatePasswordPayload.setNewPassword(newPassword);
            updateAccountInfoPayload.setUpdatePasswordPayload(updatePasswordPayload);
        }

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

    @GetMapping("detail/{id}")
    public ResultObject<AccountDetailDto> getUserInfoById(@PathVariable("id") String id) {
        AccountDetailDto accountDetailDto = accountModule.getAccountDetail(id);
        return ResponseUtil.success(accountDetailDto);
    }

    @PostMapping("details")
    public ResultObject<List<AccountDetailDto>> getUserInfoById(@RequestBody() List<String> ids) {
        List<AccountDetailDto> accountDetailDto = accountInfoService.getAccountDetailByUserId(ids);
        return ResponseUtil.success(accountDetailDto);
    }
}
