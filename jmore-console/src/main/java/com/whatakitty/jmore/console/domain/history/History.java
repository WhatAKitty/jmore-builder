package com.whatakitty.jmore.console.domain.history;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import java.util.Stack;
import lombok.RequiredArgsConstructor;

/**
 * history aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@RequiredArgsConstructor
public class History extends AbstractAggregateRoot {

    /**
     * command snapshot factory
     */
    private final CommandSnapshotFactory commandSnapshotFactory;
    /**
     * the stack of command snapshot
     */
    private final Stack<CommandSnapshot> stack = new Stack<>();
    /**
     * console context
     */
    private final ConsoleContext context;

    /**
     * add command into history stack
     *
     * @param command the command to execute successfully
     */
    public void addHistory(ICommand command) {
        stack.push(commandSnapshotFactory.snapshot(command));
    }

    /**
     * show the commands execute previously
     */
    public void showHistory() {
        int total = stack.size();
        for (int i = 0; i < total; i++) {
            CommandSnapshot snapshot = stack.get(i);
            context.getWriter().println(String.format("[%s] %s", i, snapshot.getCommand()));
        }
    }

    /**
     * rollback the current stage
     *
     * @param index the rollback index, started with 1
     */
    public void rollback(int index) {
        // check arguments
        if (index < 1 || stack.size() < index) {
            throw new IllegalArgumentException("exceed max rollback");
        }

        for (int i = 1; i < index; i++) {
            CommandSnapshot snapshot = stack.get(i);
            context.getWriter().println(snapshot.getCommand());
        }
    }

}