package com.whatakitty.jmore.framework.validation.rule;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * china phone validator
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
public class ChinaPhoneValidator implements ConstraintValidator<ChinaPhone, String> {

    /**
     * the regex of china mainland phone
     */
    private static final String CHINA_MAINLAND_PHONE_REGEX = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])|(18[0-9])|(19[8,9]))\\d{8}$";

    /**
     * the regex of china hongkong phone
     */
    private static final String CHINA_HONGKONG_PHONE_REGEX = "^(5|6|8|9)\\d{7}$";

    /**
     * the pattern of china mainland phone
     */
    private static final Pattern CHINA_MAINLAND_PHONE_PATTERN = Pattern.compile(CHINA_MAINLAND_PHONE_REGEX);

    /**
     * the pattern of china hongkong phone
     */
    private static final Pattern CHINA_HONGKONG_PHONE_PATTERN = Pattern.compile(CHINA_HONGKONG_PHONE_REGEX);


    /**
     * the annotated phone type
     */
    private ChinaPhone.PhoneType[] phoneType;

    @Override
    public void initialize(ChinaPhone constraintAnnotation) {
        this.phoneType = constraintAnnotation.phoneType();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        // match china phone
        if (CHINA_MAINLAND_PHONE_PATTERN.matcher(value).matches()) {
            return true;
        }

        // match china hongkong phone
        return CHINA_HONGKONG_PHONE_PATTERN.matcher(value).matches();
    }

}
