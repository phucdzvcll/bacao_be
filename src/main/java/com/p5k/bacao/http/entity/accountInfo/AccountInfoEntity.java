package com.p5k.bacao.http.entity.accountInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.p5k.bacao.http.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("tb_account_info")
public class AccountInfoEntity extends BaseEntity {

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "first_login")
    private String firstLogin;

    @TableField(value = "display_name")
    private String displayName;

    @TableField(value = "email")
    private String email;

    @TableField(value = "dob")
    private String dob;

    @TableField(value = "phone_number")
    private String phoneNumber;

}