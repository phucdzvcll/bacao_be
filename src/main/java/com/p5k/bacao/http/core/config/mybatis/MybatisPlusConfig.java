/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.http.core.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.PostgreDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor oa = new PaginationInnerInterceptor(DbType.POSTGRE_SQL);
        oa.setDialect(new PostgreDialect());
        interceptor.addInnerInterceptor(oa);
        return interceptor;
    }

}
