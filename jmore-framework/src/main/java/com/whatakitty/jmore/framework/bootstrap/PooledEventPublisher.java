package com.whatakitty.jmore.framework.bootstrap;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.event.ApplicationEventMulticaster;

/**
 * the publisher invoked in thread pool
 *
 * @author WhatAKitty
 * @date 2019/04/28
 * @description
 **/
public interface PooledEventPublisher extends ApplicationEventMulticaster {

    /**
     * set executor
     */
    void setExecutor(ThreadPoolExecutor threadPoolExecutor);

}
