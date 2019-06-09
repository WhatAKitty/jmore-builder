package com.whatakitty.jmore.framework.utils;

import com.whatakitty.jmore.framework.compilerule.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * stream utils
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
public final class StreamUtils {

    public static <T> Stream<T> of(@Nullable Collection<T> collection, boolean parallel) {
        final Collection<T> collectionFiltered = collection == null ? Collections.emptyList() : collection;
        return parallel ? collectionFiltered.parallelStream() : collectionFiltered.stream();
    }

    private StreamUtils() {}

}
