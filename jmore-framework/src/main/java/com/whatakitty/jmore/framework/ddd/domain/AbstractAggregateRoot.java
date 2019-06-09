package com.whatakitty.jmore.framework.ddd.domain;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import com.whatakitty.jmore.framework.utils.SpringUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;
import sun.misc.Unsafe;

/**
 * base aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
@Data
public abstract class AbstractAggregateRoot<PK> implements Serializable {

    private static final Unsafe unsafe;
    private static final long VERSION_OFFSET;

    private final AggregateId<PK> id;
    @EqualsAndHashCode.Exclude
    private AggregateStatus status = AggregateStatus.ACTIVE;
    @EqualsAndHashCode.Exclude
    private volatile Version version = Version.INITIAL_VERSION;

    public AbstractAggregateRoot(AggregateId<PK> id) {
        this.id = id.clone();
    }

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

    /**
     * increment version value
     *
     * ignore the baddest stage: cannot update version always.
     */
    public void incVersion() {
        boolean result;
        do {
            result = Unsafe.getUnsafe().compareAndSwapObject(this, VERSION_OFFSET, version, Version.of(version.get() + 1));
        } while (!result);
    }

    static {
        try {
            final Field unsafeField = Unsafe.class.getDeclaredFields()[0];
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }

        try {
            VERSION_OFFSET = unsafe.objectFieldOffset
                (AbstractAggregateRoot.class.getDeclaredField("version"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }

    }

}
