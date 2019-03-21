package com.whatakitty.jmore.demo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * demo param bean
 *
 * @author WhatAKitty
 * @date 2019/02/19
 * @description
 **/
@Data
public class DemoParam {

    @NotNull(message = "{validator.demo.name.not-null}")
    private String name;

    @NotNull(
        groups = DemoParamValidateGroup1.class,
        message = "{validator.demo.title.not-blank}"
    )
    @NotEmpty(
        groups = DemoParamValidateGroup1.class,
        message = "{validator.demo.title.not-blank}"
    )
    @Length(
        min = 1,
        max = 64,
        groups = DemoParamValidateGroup1.class,
        message = "{validator.demo.title.illegal-length-1-64}"
    )
    private String title;

    /**
     * first validation group
     */
    public interface DemoParamValidateGroup1 {}

}
