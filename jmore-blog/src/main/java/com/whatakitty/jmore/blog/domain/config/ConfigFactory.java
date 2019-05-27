package com.whatakitty.jmore.blog.domain.config;

/**
 * config factory
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public final class ConfigFactory {

    public static final ConfigFactory FACTORY = new ConfigFactory();

    /**
     * create a new config
     *
     * @param title     blog title
     * @param contact   blog contact
     * @param copyright blog copyright
     * @param domain    blog domain
     * @param username  the manager's username
     * @param password  the manager's password
     */
    public Config newConfig(String title,
                            String contact,
                            String copyright,
                            String domain,
                            String username,
                            String password) {
        Config config = new Config();
        config.setTitle(title);
        config.setContact(contact);
        config.setCopyright(copyright);
        config.setDomain(domain);
        config.setAuthor(Manager.of(username, password));
        return config;
    }

}
