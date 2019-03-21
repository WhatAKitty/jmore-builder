package com.whatakitty.jmore.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis configuration
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class MyBatisConfig {

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

}
