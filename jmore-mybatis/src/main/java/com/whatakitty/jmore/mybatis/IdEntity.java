package com.whatakitty.jmore.mybatis;

import java.io.Serializable;
import javax.persistence.Id;
import lombok.Data;

/**
 * base entity with id
 *
 * @author WhatAKitty
 * @date 2018/01/24
 * @description
 **/
@Data
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

}
