package com.whatakitty.jmore.console.domain.history;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * history factory
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@RequiredArgsConstructor
@Component
public final class HistoryFactory {

    private final CommandSnapshotFactory commandSnapshotFactory;

    /**
     * get or create history in certain context
     *
     * @param context the console context
     * @return history instance
     */
    public History getOrCreateHistory(ConsoleContext context) {
        if (context.getHistory() != null) {
            return context.getHistory();
        }
        History history = new History(commandSnapshotFactory, context);
        History oldHistory = context.setHistory(history);
        if (oldHistory == null) {
            return history;
        }
        return oldHistory;
    }

}
