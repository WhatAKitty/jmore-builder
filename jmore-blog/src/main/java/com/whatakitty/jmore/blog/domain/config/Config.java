package com.whatakitty.jmore.blog.domain.config;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * blog config aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/25
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends AbstractAggregateRoot<Long> {

    private String title;
    private String contact;
    private String copyright;
    private String domain;
    private Manager author;

    /**
     * blog init
     *
     * @param title     blog title
     * @param contact   blog contact
     * @param copyright blog copyright
     * @param domain    blog domain
     * @param username  the manager's username
     * @param password  the manager's password
     */
    public void init(String title,
                     String contact,
                     String copyright,
                     String domain,
                     String username,
                     String password) {
        this.title = title;
        this.contact = contact;
        this.copyright = copyright;
        this.domain = domain;
        this.author = Manager.of(username, password);
    }

}
