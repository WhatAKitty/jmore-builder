package com.whatakitty.jmore.framework.ddd.domain;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import com.whatakitty.jmore.framework.utils.SpringUtils;
import java.io.Serializable;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * base aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
@Data
public abstract class AbstractAggregateRoot<PK> implements Serializable {

    private AggregateId<PK> id;

    /**
     * publish event
     *
     * @param event event object
     */
    public void publishEvent(ApplicationEvent event) {
        // TODO need to use another plan instead of injecting spring dependency
        SpringUtils.getApplicationContext().publishEvent(event);
    }

}
