package com.whatakitty.jmore.console.demo;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.command.ICommandParser;
import org.springframework.stereotype.Component;

/**
 * simple demo of command parser
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@Component
public class DemoCommandParser implements ICommandParser {

    @Override
    public ICommand parse(ConsoleContext context, String command) {
        switch (command) {
            case SayHelloWorldCommand.COMMAND_TIP:
                context.setCurrentCommand(command);
                return new SayHelloWorldCommand(context);
            default:
                throw new UnsupportedOperationException(String.format("unsupported command %s", command));
        }
    }

}
