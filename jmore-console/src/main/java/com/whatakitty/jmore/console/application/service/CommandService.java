package com.whatakitty.jmore.console.application.service;

import com.whatakitty.jmore.console.domain.command.*;
import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.console.domain.history.History;
import com.whatakitty.jmore.console.domain.history.HistoryRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.List;
import java.util.Optional;
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
    private final HistoryRepository historyRepository;

    /**
     * execute command
     *
     * @param context console context
     * @param cmd     command
     */
    public Optional<CommandResult> execute(ConsoleContext context, String cmd) {
        // get or create history
        context.bindHistory(() -> {
            History newOne = new History(context);
            historyRepository.create(newOne);
            return newOne;
        });

        // execute command
        try {
            return commandRepository.findById(AggregateId.of(cmd))
                .map(command -> {
                    context.setCurrentCommand(command);
                    return command.execute(context);
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
            .map(AggregateId::of)
            .collect(Collectors.toList()));
        BatchCommand batchCommand = Command.createBatch(commands);
        return commandRepository.saveCommand(batchName, batchCommand);
    }

}
