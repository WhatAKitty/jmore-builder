package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.framework.ddd.domain.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
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
    public final BatchCommand addCommand(ICommand command) {
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
    public ICommand get() {
        return new BatchCommand(commands);
    }

    @Override
    public void execute() {
        commands.forEach(ICommand::execute);
    }

    @Override
    public void undo() {

    }

}
