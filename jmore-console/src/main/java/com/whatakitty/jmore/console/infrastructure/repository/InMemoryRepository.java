package com.whatakitty.jmore.console.infrastructure.repository;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;

/**
 * in memory repository
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
public abstract class InMemoryRepository<PK, T> {

    private final Map<AggregateId<PK>, T> holder = new ConcurrentHashMap<>(16);

    @PostConstruct
    public void postConstruct() {
        init();
    }

    protected abstract void init();

    protected T put(AggregateId<PK> key, T value) {
        return holder.put(key, value);
    }

    protected T putIfAbsent(AggregateId<PK> key, T value) {
        return holder.putIfAbsent(key, value);
    }

    protected T get(AggregateId<PK> key) {
        return holder.get(key);
    }

}
