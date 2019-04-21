package com.whatakitty.jmore.demo;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alibaba.fastjson.JSON;
import com.whatakitty.jmore.web.api.ApiAdvice;
import com.whatakitty.jmore.web.i18n.I18nConfig;
import com.whatakitty.jmore.framework.validation.ValidatorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * demo api test
 *
 * @author WhatAKitty
 * @date 2019/02/19
 * @description
 **/
@ContextConfiguration(classes = {
    DemoApiTest.DemoTestConfiguration.class,
    I18nConfig.class,
    ValidatorConfig.class
})
@RunWith(SpringRunner.class)
public class DemoApiTest {

    private final MockMvc mockMvc;

    public DemoApiTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(DemoApiImpl.class)
            .setControllerAdvice(new ApiAdvice())
            .build();
    }

    @Test
    public void statusTest() throws Exception {
        mockMvc.perform(post("/api/demo/status"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(DemoResultCode.SUCCESS.getCode())))
            .andExpect(jsonPath("$.data.ok", is("true")));
    }

    @Test
    public void exceptionTest() throws Exception {
        mockMvc.perform(post("/api/demo/exception"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(DemoResultCode.SYSTEM_ERROR.getCode())))
            .andExpect(jsonPath("$.msg", is("uncaught exception")));
    }

    @Test
    public void test_param1_succ() throws Exception {
        DemoParam demoParam = new DemoParam();
        demoParam.setName("bruce");
        demoParam.setTitle("hello");

        mockMvc.perform(
            post("/api/demo/param1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(demoParam)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(DemoResultCode.SUCCESS.getCode())))
            .andExpect(jsonPath("$.data.title", is(demoParam.getTitle())));
    }

    @Test
    public void test_param1_default() throws Exception {
        DemoParam demoParam = new DemoParam();

        mockMvc.perform(
            post("/api/demo/param1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(demoParam)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(DemoResultCode.BAD_REQUEST.getCode())));
    }

    @Test
    public void test_param1_missingbody() throws Exception {
        mockMvc.perform(
            post("/api/demo/param1").contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(DemoResultCode.BAD_REQUEST.getCode())));
    }

    @Test
    public void test_param2_group() throws Exception {
        DemoParam demoParam = new DemoParam();
        demoParam.setName("bruce");

        mockMvc.perform(
            post("/api/demo/param2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(demoParam)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(DemoResultCode.BAD_REQUEST.getCode())));
    }

    @Configuration
    public static class DemoTestConfiguration {

        @Bean
        public ResourceBundleMessageSource messageSource() {
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setBasenames("i18n/messages");
            source.setDefaultEncoding("UTF-8");
            return source;
        }

    }

}