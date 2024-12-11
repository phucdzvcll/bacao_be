package com.p5k.bacao.http.module;

import com.p5k.bacao.http.converter.accountInfo.AccountInfoConverter;
import com.p5k.bacao.http.core.constants.BaseConstant;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.exception.ServiceException;
import com.p5k.bacao.http.core.security.CustomSecurityContextHolder;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.http.dto.accountInfo.AccountDetailDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.p5k.bacao.http.core.constants.MediaTypeSupport.ALLOWED_FILE_TYPES;

@Service
@RequiredArgsConstructor
public class AccountModule {
    private final IAccountService accountService;
    private final IAccountInfoService accountInfoService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public AccountDetailDto createAccount(CreateAccountPayload createAccountPayload) {
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

        AccountDetailDto accountDetailDto = AccountInfoConverter.INSTANCE.toDto(accountInfoEntity);
        accountDetailDto.setUserName(accountEntity.getUsername());
        accountDetailDto.setFirstLogin(BaseConstant.KEY_YES);
        return accountDetailDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public AccountDetailDto updateAccount(UpdateAccountInfoPayload updateAccountInfoPayload, MultipartFile avatar) {
        UpdatePasswordPayload updatePasswordPayload = updateAccountInfoPayload.getUpdatePasswordPayload();
        AccountEntity accountEntity = accountService.getById(CustomSecurityContextHolder.getUserId());

        XChecker.isNullThruMsg(accountEntity, ServiceCodeEnum.USER_EXCEPTION_USER_NOT_FOUND);

        if (XChecker.isNotNull(updatePasswordPayload) &&
                XChecker.isNotEmpty(updatePasswordPayload.getNewPassword())) {
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


        try {
            if (avatar != null) {
                String fileType = avatar.getContentType();

                if (fileType == null || !ALLOWED_FILE_TYPES.contains(fileType)) {
                    throw new ServiceException(ServiceCodeEnum.FILE_EXCEPTION_DOCUMENT_TYPE_NOT_SUPPORT);
                }
                accountInfoEntity.setAvatar(saveAvatarFile(avatar, accountEntity.getUsername(), accountEntity.getId()));
            }
        } catch (ServiceException e){
            throw e;
        }catch (Exception e) {
            throw new ServiceException(e.toString());
        }

        accountInfoService.updateById(accountInfoEntity);
        AccountDetailDto accountDetailDto = AccountInfoConverter.INSTANCE.toDto(accountInfoEntity);
        accountDetailDto.setUserName(accountEntity.getUsername());

        return accountDetailDto;
    }

    private String saveAvatarFile(MultipartFile avatar, String userName, String userId) throws Exception {
        String oldAvatar = accountInfoService.getAvatar(userId);

        String uploadDir = "uploads/avatars/";
        if (oldAvatar != null) {
            File existingFile = new File(oldAvatar);
            existingFile.delete();
        }
        // Thư mục lưu ảnh
        Path uploadPath = Paths.get(uploadDir);


        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        avatar.getOriginalFilename();

        String fileName = userName + "_" + "avatar." + Objects.requireNonNull(avatar.getOriginalFilename()).substring(avatar.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(avatar.getInputStream(), filePath);
        return filePath.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    public AccountDetailDto getAccountDetail() {
        return accountInfoService.getAccountDetailByUserId(CustomSecurityContextHolder.getUserId());
    }
}
