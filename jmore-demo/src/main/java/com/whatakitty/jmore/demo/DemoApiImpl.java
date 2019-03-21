package com.whatakitty.jmore.demo;

import com.whatakitty.jmore.framework.api.Result;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@RestController
public class DemoApiImpl implements DemoApi {

    @Override
    public Object status() {
        Map<String, String> data = new HashMap<>(2);
        data.put("ok", "true");
        return Result.getSuccResult(data);
    }

    @Override
    public Object exception() {
        throw new RuntimeException("uncaught exception");
    }

    @Override
    public Object param1(DemoParam demoParam) {
        return Result.getSuccResult(demoParam);
    }

    @Override
    public Object param2(DemoParam demoParam) {
        return Result.getSuccResult(demoParam);
    }

}
