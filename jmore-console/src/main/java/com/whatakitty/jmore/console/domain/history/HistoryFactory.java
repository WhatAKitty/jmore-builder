package com.whatakitty.jmore.console.domain.history;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.springframework.stereotype.Component;

/**
 * history factory
 *
 * @author WhatAKitty
 * @date 2019/05/30
 * @description
 **/
@Component
public final class HistoryFactory {

    private static final AggregateId<String> HISTORY_ID = AggregateId.of("HISTORY");

    public History create(ConsoleContext context) {
        return new History(HISTORY_ID, context);
    }

}
