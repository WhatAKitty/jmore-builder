package com.whatakitty.jmore.framework.ddd.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * base entity
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@Data
public abstract class AbstractEntity<PK> implements Serializable {

    private PK id;

}
