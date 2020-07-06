package com.whatakitty.jmore.dfs.client.api.domain;

/**
 * the stored data in dfs like picture, file and so on named Object
 *
 * @author WhatAKitty
 * @date 2020/07/05
 * @since 1.0.0
 **/
public abstract class Object<T extends ObjectKey<?>> {

    private final T objectKey;

    protected Object(T objectKey) {
        this.objectKey = objectKey;
    }

    public abstract String getUrl();

    public final java.lang.Object getKey() {
        return objectKey.getKey();
    }

}
