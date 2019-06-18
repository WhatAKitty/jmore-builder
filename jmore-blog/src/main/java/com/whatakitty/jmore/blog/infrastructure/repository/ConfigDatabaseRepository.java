package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.config.Config;
import com.whatakitty.jmore.blog.domain.config.ConfigRepository;
import com.whatakitty.jmore.blog.domain.config.Manager;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.config.ConfigDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.config.ConfigMapper;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserMapper;
import com.whatakitty.jmore.framework.ddd.domain.AggregateStatus;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * config database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@RequiredArgsConstructor
@Component
public class ConfigDatabaseRepository implements ConfigRepository {

    private static final Config EMPTY_CONFIG;
    private static final long CONFIG_ID = 1L;

    private final ConfigMapper configMapper;
    private final UserMapper userMapper;

    @Override
    public AggregateId<Long> nextId() {
        return AggregateId.of(CONFIG_ID);
    }

    @Override
    public Config load() {
        final UserDO userDO = userMapper.selectFirst();
        final ConfigDO configDO = configMapper.selectByPrimaryKey(CONFIG_ID);

        if (userDO == null || configDO == null) {
            return EMPTY_CONFIG;
        }

        final Config config = new Config(AggregateId.of(configDO.getId()));
        config.setAuthor(Manager.of(userDO.getUsername(), userDO.getPassword(), userDO.getMobile()));
        config.setTitle(configDO.getTitle());
        config.setContact(configDO.getContactEmail());
        config.setCopyright(configDO.getCopyright());
        config.setDomain(configDO.getDomain());
        return config;
    }

    @Override
    public void create(Config config) {
        final ConfigDO configDO = new ConfigDO();
        configDO.setTitle(config.getTitle());
        configDO.setContactEmail(config.getContact());
        configDO.setCopyright(config.getCopyright());
        configDO.setDomain(config.getDomain());
        configMapper.insert(configDO);
    }

    static {
        EMPTY_CONFIG = new Config(AggregateId.of(-1L));
        EMPTY_CONFIG.setStatus(AggregateStatus.INVALID);
    }

}
