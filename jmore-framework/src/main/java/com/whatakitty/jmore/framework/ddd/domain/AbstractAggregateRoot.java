package com.whatakitty.jmore.framework.ddd.domain;

import com.whatakitty.jmore.framework.compilerule.annotations.NotNull;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.io.Serializable;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

/**
 * base aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
@Data
public abstract class AbstractAggregateRoot implements Serializable, ApplicationContextAware {

    private AggregateId id;
    private ApplicationContext applicationContext;

    /**
     * publish event
     *
     * @param event event object
     */
    public void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
