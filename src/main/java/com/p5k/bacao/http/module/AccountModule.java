package com.p5k.bacao.http.module;

import com.p5k.bacao.http.converter.accountInfo.AccountInfoConverter;
import com.p5k.bacao.http.core.constants.BaseConstant;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.exception.ServiceException;
import com.p5k.bacao.http.core.security.CustomSecurityContextHolder;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.http.dto.accountInfo.AccountInfoDto;
import com.p5k.bacao.http.entity.account.AccountEntity;
import com.p5k.bacao.http.entity.accountInfo.AccountInfoEntity;
import com.p5k.bacao.http.payload.account.CreateAccountPayload;
import com.p5k.bacao.http.payload.account.UpdatePasswordPayload;
import com.p5k.bacao.http.payload.accountInfo.UpdateAccountInfoPayload;
import com.p5k.bacao.http.service.account.IAccountService;
import com.p5k.bacao.http.service.accountInfo.IAccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountModule {
    private final IAccountService accountService;
    private final IAccountInfoService accountInfoService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public AccountInfoDto createAccount(CreateAccountPayload createAccountPayload) {
        AccountEntity accountEntity = new AccountEntity();
        try {
            accountEntity.setUsername(createAccountPayload.getUsername());
            String encode = passwordEncoder.encode(createAccountPayload.getPassword());
            accountEntity.setPassword(encode);
            accountService.save(accountEntity);
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ServiceCodeEnum.USER_EXCEPTION_USER_NAME_DUPLICATED);
        }
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setUserId(accountEntity.getId());
        accountInfoService.save(accountInfoEntity);

        AccountInfoDto accountInfoDto = AccountInfoConverter.INSTANCE.toDto(accountInfoEntity);
        accountInfoDto.setUserName(accountEntity.getUsername());
        accountInfoDto.setFirstLogin(BaseConstant.KEY_YES);
        return accountInfoDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public AccountInfoDto updateAccount(UpdateAccountInfoPayload updateAccountInfoPayload) {
        UpdatePasswordPayload updatePasswordPayload = updateAccountInfoPayload.getUpdatePasswordPayload();
        AccountEntity accountEntity = accountService.getById(CustomSecurityContextHolder.getUserId());

        XChecker.isNullThruMsg(accountEntity, ServiceCodeEnum.USER_EXCEPTION_USER_NOT_FOUND);

        if (XChecker.isNotEmpty(updatePasswordPayload.getNewPassword())) {
            XChecker.isFalseThruMsg(
                    passwordEncoder.matches(updatePasswordPayload.getOldPassword(), accountEntity.getPassword())
                    , ServiceCodeEnum.USER_EXCEPTION_OLD_PASSWORD_NOT_MATCHING);
            accountEntity.setPassword(passwordEncoder.encode(updatePasswordPayload.getNewPassword()));
            accountService.updateById(accountEntity);
        }

        AccountInfoEntity accountInfoEntity = accountInfoService.getAccountInfoByUserId(CustomSecurityContextHolder.getUserId());

        accountInfoEntity.setFirstLogin(BaseConstant.KEY_NO);

        if (XChecker.isNotEmpty(updateAccountInfoPayload.getDisplayName())) {
            accountInfoEntity.setDisplayName(updateAccountInfoPayload.getDisplayName());
        }
        if (XChecker.isNotEmpty(updateAccountInfoPayload.getEmail())) {
            accountInfoEntity.setEmail(updateAccountInfoPayload.getEmail());
        }

        if (XChecker.isNotEmpty(updateAccountInfoPayload.getPhoneNumber())) {
            accountInfoEntity.setPhoneNumber(updateAccountInfoPayload.getPhoneNumber());
        }
        if (XChecker.isNotEmpty(updateAccountInfoPayload.getDob())) {
            accountInfoEntity.setDob(updateAccountInfoPayload.getDob());
        }

        accountInfoService.updateById(accountInfoEntity);
        AccountInfoDto accountInfoDto = AccountInfoConverter.INSTANCE.toDto(accountInfoEntity);
        accountInfoDto.setUserName(accountEntity.getUsername());
        return accountInfoDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public AccountInfoDto getUserInfo(){
        return accountInfoService.getAccountInfoDtoByUserId(CustomSecurityContextHolder.getUserId());
    }
}
