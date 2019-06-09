package com.whatakitty.jmore.framework.ddd.publishedlanguage;

import java.io.Serializable;
import lombok.Value;

/**
 * aggregate root id
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@Value(staticConstructor = "of")
public final class AggregateId<T> implements Serializable, Cloneable {

    private T id;

    @Override
    public AggregateId<T> clone() {
        return AggregateId.of(id);
    }

}
