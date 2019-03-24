package com.whatakitty.jmore.demo.infrastructure.repository.converters;

import com.whatakitty.jmore.demo.domain.model.user.User;
import com.whatakitty.jmore.demo.infrastructure.dao.entity.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * user converter
 *
 * @author WhatAKitty
 * @date 2019/03/01
 * @description
 **/
@Mapper
public interface UserConverter {

    /**
     * convert {@link UserDO} to {@link User}
     *
     * @param userDO userDO instance
     * @return converted user instance
     */
//    @Mappings({
//        @Mapping(source = "username", target = "accountInfo.username"),
//        @Mapping(source = "password", target = "accountInfo.password"),
//        @Mapping(source = "expired", target = "accountInfo.expired"),
//        @Mapping(source = "locked", target = "accountInfo.locked"),
//        @Mapping(source = "enabled", target = "accountInfo.enabled")
//    })
    User fromDO(UserDO userDO);

}
