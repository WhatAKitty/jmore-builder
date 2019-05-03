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
@Value
public class AggregateId<T> implements Serializable {

    private T id;

}
