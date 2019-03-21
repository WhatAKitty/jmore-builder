package com.whatakitty.jmore.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/02/24
 * @description
 **/
@Controller
@RequestMapping("/demo")
public class SecurityController {

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login_page");
        modelAndView.addObject("aa", "bb");
        return modelAndView;
    }

    @RequestMapping("/home")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
