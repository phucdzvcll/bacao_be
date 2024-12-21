package com.p5k.bacao.http.core.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.p5k.bacao.http.core.security.CustomSecurityContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("systime", LocalDateTime.now());
        metaObject.setValue("createdBy", CustomSecurityContextHolder.getUserId());
        metaObject.setValue("createdName", CustomSecurityContextHolder.getUsername());
        metaObject.setValue("createdAt", LocalDateTime.now());
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updatedBy", CustomSecurityContextHolder.getUserId());
        metaObject.setValue("updatedName", CustomSecurityContextHolder.getUsername());
        metaObject.setValue("updatedAt", LocalDateTime.now());
    }

}
