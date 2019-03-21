package com.whatakitty.jmore.mybatis;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * base entity with soft delete
 *
 * @author WhatAKitty
 * @date 2017/6/11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SDelEntity extends BaseEntity {

    public static final String DEL_LABEL = "isDel";

    /**
     * 未删除
     */
    public static final boolean DEL_FALSE = false;

    /**
     * 已删除
     */
    public static final boolean DEL_TRUE = true;

    /**
     * 是否已经被删除
     */
    private Boolean isDel;

}
