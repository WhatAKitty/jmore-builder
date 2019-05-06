package com.whatakitty.jmore.framework.validation.rule;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * china idcard validator test
 *
 * @author WhatAKitty
 * @date 2019/05/06
 * @description
 **/
public class ChinaIDCardValidatorTest {

    private ChinaIDCardValidator validator = new ChinaIDCardValidator();

    @Test
    public void test_validate_passed() {
        Assert.assertTrue(validator.isValid("330227199008170014", null));
    }

    @Test
    public void test_validate_15length_passed() {
        Assert.assertTrue(validator.isValid("350424870506202", null));
    }

    @Test
    public void test_validate_nopass() {
        Assert.assertFalse(validator.isValid("123456789012345678", null));
        Assert.assertFalse(validator.isValid("330227199013170014", null));
        Assert.assertFalse(validator.isValid("330227199008390014", null));
        Assert.assertFalse(validator.isValid("3339", null));
    }

}