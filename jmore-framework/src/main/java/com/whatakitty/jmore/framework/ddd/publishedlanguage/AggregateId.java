package com.whatakitty.jmore.framework.ddd.publishedlanguage;

import java.io.Serializable;
import lombok.Data;

/**
 * aggregate root id
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@Data
public class AggregateId implements Serializable {

    private Long id;

}
