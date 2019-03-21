package com.whatakitty.jmore.demo.domain.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * user sex
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@RequiredArgsConstructor
@Getter
public enum UserSex {

    /**
     * boy
     */
    BOY(1, "boy"),
    /**
     * girl
     */
    GIRL(2, "girl"),
    /**
     * unknown
     */
    UNKNOWN(9, "unknown");

    private final int value;
    private final String description;


}
