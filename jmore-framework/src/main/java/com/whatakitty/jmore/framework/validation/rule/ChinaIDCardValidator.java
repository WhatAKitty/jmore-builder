package com.whatakitty.jmore.framework.validation.rule;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * china idcard validator
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
public class ChinaIDCardValidator implements ConstraintValidator<ChinaIDCard, String> {

    /**
     * the regex of china idcard
     */
    private static final String CHINA_IDCARD_REGEX = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

    /**
     * the pattern of china idcard
     */
    private static final Pattern CHINA_IDCARD_REGEX_PATTERN = Pattern.compile(CHINA_IDCARD_REGEX);


    @Override
    public void initialize(ChinaIDCard constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        // match china idcard
        return CHINA_IDCARD_REGEX_PATTERN.matcher(value).matches();
    }

}
