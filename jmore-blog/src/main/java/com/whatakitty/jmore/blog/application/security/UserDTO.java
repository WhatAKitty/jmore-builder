package com.whatakitty.jmore.blog.application.security;

import lombok.Data;

/**
 * user dto
 *
 * @author WhatAKitty
 * @date 2019/06/19
 * @description
 **/
@Data
public class UserDTO {

    private String password;
    private String username;
    private String mobile;

}
