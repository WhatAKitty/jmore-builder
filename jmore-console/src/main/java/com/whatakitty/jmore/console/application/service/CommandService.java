package com.whatakitty.jmore.console.application.service;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.CommandFactory;
import com.whatakitty.jmore.console.domain.command.ICommand;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * command service
 *
 * execute command and add execute history
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@RequiredArgsConstructor
@Service
public class CommandService {

    private final CommandFactory commandFactory;

    public void execute(ConsoleContext context, String cmd) {
        ICommand command = commandFactory.create(context, Collections.singletonList(cmd));
        command.execute();
    }

}
