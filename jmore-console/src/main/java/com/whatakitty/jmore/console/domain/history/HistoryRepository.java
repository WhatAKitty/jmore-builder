package com.whatakitty.jmore.console.domain.history;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.Optional;

/**
 * history repository
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
public interface HistoryRepository {

    /**
     * find history by history id
     *
     * @param historyId history id
     * @return history instance
     */
    Optional<History> findById(AggregateId<String> historyId);

    /**
     * persist history
     *
     * @param history history
     */
    void create(History history);

}
