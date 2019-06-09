package com.whatakitty.jmore.framework.ddd.publishedlanguage;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * aggregate id test
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
public class AggregateIdTest {

    @Test
    public void test_clone_normal() {
        AggregateId<Long> id = AggregateId.of(1L);
        AggregateId<Long> cloned = id.clone();
        Assert.assertEquals(id.getId(), cloned.getId());
        Assert.assertEquals(System.identityHashCode(id.getId()), System.identityHashCode(cloned.getId()));
    }



}