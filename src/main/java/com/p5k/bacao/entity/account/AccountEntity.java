package com.p5k.bacao.entity.account;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.p5k.bacao.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("tb_account")
public class AccountEntity extends BaseEntity  {

    @TableField(value = "user_name")
    private String username;

    @TableField(value = "password")
    private String password;

}
