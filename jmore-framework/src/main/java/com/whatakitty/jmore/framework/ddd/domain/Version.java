package com.whatakitty.jmore.framework.ddd.domain;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

/**
 * version
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public final class Version extends ValueObject {

    public static final Version INITIAL_VERSION = Version.of(0);

    private final int version;

    public int get() {
        return version;
    }

}
