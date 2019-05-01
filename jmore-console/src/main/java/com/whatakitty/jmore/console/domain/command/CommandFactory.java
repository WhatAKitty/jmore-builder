package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.ConsoleContext;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The factory to create command
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
@Component
@RequiredArgsConstructor
public class CommandFactory {

    /**
     * command parser interface inject
     */
    private final ICommandParser parser;

    /**
     * parse user command and get an instance of command.
     *
     * @param context console context
     * @param command user command
     * @return command instance
     */
    public ICommand create(ConsoleContext context, List<String> command) {
        if (command == null || command.isEmpty()) {
            throw new IllegalArgumentException("command count at least greater than 0");
        } else if (command.size() == 1) {
            return parser.parse(context, command.get(0));
        } else {
            return new BatchCommand(command.parallelStream()
                .map(cmd -> parser.parse(context, cmd))
                .collect(Collectors.toList()));
        }
    }

}
