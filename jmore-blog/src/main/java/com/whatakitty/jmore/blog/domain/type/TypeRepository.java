package com.whatakitty.jmore.blog.domain.type;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * type repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface TypeRepository {

    /**
     * get the next id for type
     *
     * @return
     */
    AggregateId<Long> nextId();

    /**
     * add type into repository
     *
     * @param type
     */
    void add(Type type);

    /**
     * remove the type from repository
     *
     * @param type
     */
    void remove(Type type);

}
