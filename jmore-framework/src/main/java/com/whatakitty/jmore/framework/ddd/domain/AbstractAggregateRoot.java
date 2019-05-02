package com.whatakitty.jmore.framework.ddd.domain;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.io.Serializable;
import lombok.Data;

/**
 * base aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
@Data
public abstract class AbstractAggregateRoot implements Serializable {

    private AggregateId id;

}
