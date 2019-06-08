package com.whatakitty.jmore.blog.infrastructure.repository.mybatis;

import com.whatakitty.jmore.mybatis.mapper.InsertListMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * mybatis configuration
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Configuration
@MapperScan(
    basePackages = "com.whatakitty.jmore.blog.infrastructure.repository.mybatis",
    mapperHelperRef = "mapperHelper"
)
public class MybatisConfiguration {

    @Bean
    public MapperHelper mapperHelper() {
        Config config = new Config();
        List<Class> mappers = new ArrayList<>();
        mappers.add(Mapper.class);
        mappers.add(InsertListMapper.class);
        config.setMappers(mappers);

        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(config);
        return mapperHelper;
    }

}
