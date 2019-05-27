package com.whatakitty.jmore.blog.domain.resource;

/**
 * resource repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface ResourceRepository {

    /**
     * add resource into repository
     *
     * @param resource
     */
    void add(Resource resource);

    /**
     * remove the resource from repository
     *
     * @param resource
     */
    void remove(Resource resource);

}
