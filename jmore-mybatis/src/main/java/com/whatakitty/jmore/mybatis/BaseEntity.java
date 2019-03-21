package com.whatakitty.jmore.mybatis;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * base entity with create date and modify date
 *
 * @author WhatAKitty
 * @version 1.0
 * @date 2016/9/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends IdEntity {

    private Date gmtCreated;
    private Date gmtModified;

}
