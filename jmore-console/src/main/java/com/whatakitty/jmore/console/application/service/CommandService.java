package com.whatakitty.jmore.console.application.service;

import com.whatakitty.jmore.console.domain.command.BatchCommand;
import com.whatakitty.jmore.console.domain.command.Command;
import com.whatakitty.jmore.console.domain.command.CommandRepository;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.List;
import java.util.stream.Collectors;
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

    private final CommandRepository commandRepository;

    /**
     * execute command
     *
     * @param context console context
     * @param cmd     command
     */
    public void execute(ConsoleContext context, String cmd) {
        try {
            commandRepository.findById(new AggregateId<>(cmd))
                .ifPresent(command -> {
                    context.setCurrentCommand(command);
                    command.execute(context);
                });
        } finally {
            context.removeCommand();
        }
    }

    /**
     * create batch command
     *
     * @param context   console context
     * @param batchName batch command name
     * @param cmds      user defined command list
     * @return create successfully
     */
    public boolean createBatch(ConsoleContext context, String batchName, List<String> cmds) {
        List<ICommand> commands = commandRepository.findInIds(cmds.parallelStream()
            .map(AggregateId::new)
            .collect(Collectors.toList()));
        BatchCommand batchCommand = Command.createBatch(commands);
        return commandRepository.saveCommand(batchName, batchCommand);
    }

}
