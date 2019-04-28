package com.whatakitty.jmore.console.demo;

import com.whatakitty.jmore.framework.bootstrap.event.JMoreStartedEvent;
import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;
import lombok.extern.slf4j.Slf4j;

/**
 * jmore console demo started listener
 *
 * @author WhatAKitty
 * @date 2019/04/28
 * @description
 **/
@Slf4j
public class ConsoleDemoStartedListener implements JMoreApplicationListener<JMoreStartedEvent> {

    @Override
    public void onApplicationEvent(JMoreStartedEvent event) {
        log.info("jmore application started at {}", event.getTimestamp());
    }

}
