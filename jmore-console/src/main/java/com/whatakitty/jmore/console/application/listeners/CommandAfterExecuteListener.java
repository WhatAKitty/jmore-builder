package com.whatakitty.jmore.console.application.listeners;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.command.event.CommandAfterExecuteEvent;
import com.whatakitty.jmore.console.domain.history.History;
import com.whatakitty.jmore.console.domain.history.HistoryFactory;
import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;
import com.whatakitty.jmore.framework.compilerule.annotations.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * add history after command execute successfully
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@RequiredArgsConstructor
@Component
public class CommandAfterExecuteListener implements JMoreApplicationListener<CommandAfterExecuteEvent> {

    private final HistoryFactory historyFactory;

    @Override
    public void onApplicationEvent(@NotNull CommandAfterExecuteEvent event) {
        ConsoleContext context = event.getContext();
        ICommand command = event.getCommand();

        // get or create history
        History history = historyFactory.getOrCreateHistory(context);

        // add command into history
        history.addHistory(command);
    }

}
