package com.whatakitty.jmore.blog.domain.type;

/**
 * type repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface TypeRepository {

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
