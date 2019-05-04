package com.whatakitty.jmore.console.infrastructure.repository;

import com.whatakitty.jmore.console.domain.history.History;
import com.whatakitty.jmore.console.domain.history.HistoryRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.Optional;
import java.util.UUID;

/**
 * default history repository
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
public class DefaultHistoryRepository extends InMemoryRepository<String, History> implements HistoryRepository {

    @Override
    public Optional<History> findById(AggregateId<String> historyId) {
        return Optional.ofNullable(get(historyId));
    }

    @Override
    public void create(History history) {
        final AggregateId<String> historyId = createId();
        history.setId(historyId);
        put(historyId, history);
    }

    @Override
    protected void init() {
        // do nothing
    }

    private AggregateId<String> createId() {
        return new AggregateId<>(UUID.randomUUID().toString());
    }

}
