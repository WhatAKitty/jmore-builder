package com.whatakitty.jmore.console.demo.application.listener;

import com.whatakitty.jmore.console.domain.command.event.CommandFinishedEvent;
import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;

/**
 * console command execute finished listener
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class ConsoleDemoCommandExecuteFinishedListener implements JMoreApplicationListener<CommandFinishedEvent> {

    @Override
    public void onApplicationEvent(CommandFinishedEvent event) {
        System.out.println(String.format("command %s has finished", event.getCommand().getName()));
    }

}
