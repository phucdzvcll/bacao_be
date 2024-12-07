package com.p5k.bacao.http.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.p5k.bacao.http.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_refresh_token")
public class RefreshTokenEntity extends BaseEntity {

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "token")
    private String token;

    @TableField(value = "expiry_date")
    private Instant expiryDate;

//    // 1: customer; 2: user administrator
//    @TableField(value = "user_type")
//    private String userType;
}
