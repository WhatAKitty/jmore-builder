package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.domain.AbstractEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

/**
 * Batch command
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
public class BatchCommand extends AbstractEntity implements ICommand {

    private final List<ICommand> commands;

    /**
     * create batch command with empty array list which has 16 capacity
     */
    public BatchCommand() {
        this.commands = new ArrayList<>(16);
    }

    /**
     * create batch command with given commands list
     *
     * @param commands command list that user given
     */
    public BatchCommand(final List<ICommand> commands) {
        this.commands = new ArrayList<>(commands);
    }

    /**
     * add command into batch mode
     *
     * @param command concreate command
     * @return batch command instance
     */
    public final BatchCommand addCommand(Command command) {
        commands.add(command);
        // collections sort
        AnnotationAwareOrderComparator.sort(commands);
        return this;
    }

    @Override
    public String getName() {
        return "BatchCommand";
    }

    @Override
    public CommandResult execute(ConsoleContext context) {
        List<CommandResult> results = commands.stream()
            .map(item -> item.execute(context))
            .collect(Collectors.toList());
        return CommandResult.of(results.parallelStream().allMatch(CommandResult::isSucc), results);
    }

    @Override
    public void undo(ConsoleContext context) {
        if (!supportUndo()) {
            throw new UnsupportedOperationException("unsupported undo operation");
        }
        
        List<ICommand> snapshots = this.commands.parallelStream().collect(Collectors.toList());
        Collections.reverse(snapshots);
        snapshots.forEach(item -> item.undo(context));
    }

    @Override
    public boolean supportUndo() {
        return this.commands.parallelStream().allMatch(item -> item.supportUndo());
    }

}
