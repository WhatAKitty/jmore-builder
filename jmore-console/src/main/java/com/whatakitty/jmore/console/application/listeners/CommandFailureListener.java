package com.whatakitty.jmore.console.application.listeners;

import com.whatakitty.jmore.console.domain.command.event.CommandExecuteFailedEvent;
import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;
import com.whatakitty.jmore.framework.compilerule.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * command failure listener
 *
 * @author WhatAKitty
 * @date 2019/05/04
 * @description
 **/
@Component
public class CommandFailureListener implements JMoreApplicationListener<CommandExecuteFailedEvent> {

    @Override
    public void onApplicationEvent(@NotNull CommandExecuteFailedEvent event) {
        event.getContext()
            .getWriter()
            .println(
                String.format("command %s execute failed with %s",
                    event.getCommand().getName(),
                    event.getThrowable().getMessage()
                ));
    }

}
