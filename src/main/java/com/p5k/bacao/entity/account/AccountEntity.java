package com.p5k.bacao.entity.account;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.p5k.bacao.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("tb_account")
public class AccountEntity extends BaseEntity  {

    @TableField(value = "user_name")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "verification_token")
    private String verificationToken;

    @TableField(value = "login_fail")
    private int loginFail;

    @TableField(value = "reset_token")
    private String resetToken;

    @TableField(value = "reset_token_time")
    private LocalDateTime resetTokenTime;

    @TableField(value = "password_expired")
    private String passwordExpired;

    @TableField(value = "login_fir_dt")
    private LocalDateTime loginFirDt;

    @TableField(value = "login_lst_dt")
    private LocalDateTime loginLstDt;

}
