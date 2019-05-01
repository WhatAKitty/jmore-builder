package com.whatakitty.jmore.console.demo;

import com.whatakitty.jmore.console.event.CommandFinishedEvent;
import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class ConsoleDemoCommandExecuteFinishedListener implements JMoreApplicationListener<CommandFinishedEvent> {

    @Override
    public void onApplicationEvent(CommandFinishedEvent event) {
        System.out.println(String.format("command %s has finished", event.getCommand()));
    }
}
