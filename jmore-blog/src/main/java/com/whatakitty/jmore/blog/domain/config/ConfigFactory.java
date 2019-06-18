package com.whatakitty.jmore.blog.domain.config;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * config factory
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public final class ConfigFactory {

    public static final ConfigFactory FACTORY = new ConfigFactory();
    private static final AggregateId<Long> DEFAULT_PK = AggregateId.of(1L);

    /**
     * create a new config
     *
     * @param title     blog title
     * @param contact   blog contact
     * @param copyright blog copyright
     * @param domain    blog domain
     * @param username  the manager's username
     * @param password  the manager's password
     * @param mobile    the manager's mobile number
     */
    public Config newConfig(String title,
                            String contact,
                            String copyright,
                            String domain,
                            String username,
                            String password,
                            String mobile) {
        Config config = new Config(DEFAULT_PK);
        config.setTitle(title);
        config.setContact(contact);
        config.setCopyright(copyright);
        config.setDomain(domain);
        config.setAuthor(Manager.of(username, password, mobile));
        return config;
    }

}
