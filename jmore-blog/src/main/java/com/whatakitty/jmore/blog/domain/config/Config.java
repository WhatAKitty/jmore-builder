package com.whatakitty.jmore.blog.domain.config;

import com.whatakitty.jmore.blog.domain.config.event.ConfigInitFinishedEvent;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * blog config aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/25
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends AbstractAggregateRoot<Long> {

    private String title;
    private String contact;
    private String copyright;
    private String domain;
    private Manager author;

    /**
     * blog init
     */
    public boolean init() {
        publishEvent(new ConfigInitFinishedEvent(this));
        return true;
    }

}
