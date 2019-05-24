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
    private AggregateStatus status = AggregateStatus.ACTIVE;

    /**
     * publish event
     *
     * @param event event object
     */
    protected void publishEvent(ApplicationEvent event) {
        // TODO need to use another plan instead of injecting spring dependency
        SpringUtils.getApplicationContext().publishEvent(event);
    }

    /**
     * active this aggregate root
     */
    protected void active() {
        this.status = AggregateStatus.ACTIVE;
    }

    /**
     * invalid this aggregate root
     */
    protected void invalid() {
        this.status = AggregateStatus.INVALID;
    }

    /**
     * check the aggregate root status, if {invalid} throw {@link IllegalAggregateStatus}
     */
    protected void checkActive() {
        if (!AggregateStatus.ACTIVE.equals(this.status)) {
            throw new IllegalAggregateStatus();
        }
    }

}
