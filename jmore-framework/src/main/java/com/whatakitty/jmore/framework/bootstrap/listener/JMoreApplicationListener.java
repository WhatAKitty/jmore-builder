package com.whatakitty.jmore.framework.bootstrap.listener;

import com.whatakitty.jmore.framework.bootstrap.event.JMoreApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * JMore application listener
 *
 * @author WhatAKitty
 * @date 2019/04/28
 * @description
 **/
@FunctionalInterface
public interface JMoreApplicationListener<E extends JMoreApplicationEvent> extends ApplicationListener<E> {

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    void onApplicationEvent(E event);

}
