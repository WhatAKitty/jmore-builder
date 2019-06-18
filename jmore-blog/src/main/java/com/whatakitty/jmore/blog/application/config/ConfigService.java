package com.whatakitty.jmore.blog.application.config;

import com.whatakitty.jmore.blog.domain.config.Config;
import com.whatakitty.jmore.blog.domain.config.ConfigFactory;
import com.whatakitty.jmore.blog.domain.config.ConfigRepository;
import com.whatakitty.jmore.framework.ddd.domain.IllegalAggregateStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * config service
 *
 * @author WhatAKitty
 * @date 2019/06/19
 * @description
 **/
@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigRepository configRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void init(ConfigDTO configDTO) {
        Config existedConfig = configRepository.load();
        try {
            existedConfig.checkActive();
            return;
        } catch (IllegalAggregateStatus e) {
            // not exists
        }

        Config config = ConfigFactory.FACTORY.newConfig(
            configDTO.getTitle(),
            configDTO.getContact(),
            configDTO.getCopyright(),
            configDTO.getDomain(),
            configDTO.getAdmin(),
            configDTO.getPassword(),
            configDTO.getMobile()
        );
        config.init();
        configRepository.create(config);
    }

}
