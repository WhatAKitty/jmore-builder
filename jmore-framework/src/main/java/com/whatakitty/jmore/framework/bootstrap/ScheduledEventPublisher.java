package com.whatakitty.jmore.framework.bootstrap;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * scheduled event publisher
 *
 * @author WhatAKitty
 * @date 2019/04/28
 * @description
 **/
public abstract class ScheduledEventPublisher
    extends AbstractApplicationEventMulticaster
    implements PooledEventPublisher {

    @Nullable
    @Getter(AccessLevel.PRIVATE)
    private ThreadPoolExecutor executor;
    private final String cron;

    public ScheduledEventPublisher(String cron) {
        this.cron = cron;
    }

    @Override
    public void setExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.executor = threadPoolExecutor;
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        multicastEvent(event, resolveDefaultEventType(event));
    }

    /**
     * TODO cron usage
     *
     * @param event
     * @param eventType
     */
    @Override
    public void multicastEvent(final ApplicationEvent event, @Nullable ResolvableType eventType) {
        ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
        for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
            Executor executor = getExecutor();
            if (executor != null) {
                executor.execute(() -> invokeListener(listener, event));
            } else {
                invokeListener(listener, event);
            }
        }
    }

    protected abstract void invokeListener(ApplicationListener<?> listener, ApplicationEvent event);

    private ResolvableType resolveDefaultEventType(ApplicationEvent event) {
        return ResolvableType.forInstance(event);
    }

}
