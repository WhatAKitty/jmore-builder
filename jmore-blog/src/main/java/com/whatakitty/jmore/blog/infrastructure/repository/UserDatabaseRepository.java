package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserRepository;
import com.whatakitty.jmore.blog.domain.security.UserType;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserMapper;
import com.whatakitty.jmore.framework.ddd.domain.AggregateStatus;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * user database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@RequiredArgsConstructor
@Component
public class UserDatabaseRepository implements UserRepository {

    private static final User ANONYMOUS;

    private final UserMapper userMapper;

    private final IdDatabaseRepository idDatabaseRepository;

    @Override
    public AggregateId<Long> nextId() {
        return AggregateId.of(idDatabaseRepository.nextId());
    }

    @Override
    public User findWithUsername(String username) {
        final UserDO userDO = userMapper.selectAllByUsername(username);

        if (null == userDO) {
            return ANONYMOUS;
        }

        final User user = new User(AggregateId.of(userDO.getId()));
        user.setUsername(userDO.getUsername());
        user.setPassword(userDO.getPassword());
        user.setNickname(userDO.getNickName());
        user.setMobile(userDO.getMobile());
        user.setBirthday(userDO.getBirthday());
        user.setLastLoginDate(userDO.getLastLoginDate());
        user.setLastLoginIp(userDO.getLastLoginIpv4());
        user.setUserType(UserType.AUTHOR);
        return user;
    }

    @Override
    public void add(User user) {
        final Date date = new Date();
        final UserDO userDO = new UserDO();

        userDO.setId(user.getId().getId());
        userDO.setUsername(user.getUsername());
        userDO.setPassword(user.getPassword());
        userDO.setUserType(UserType.AUTHOR.getType().getValue());
        userDO.setGmtCreate(date);
        userDO.setGmtModified(date);
        userMapper.insertSelective(userDO);

        userDO.setNickName(user.getNickname());
        userDO.setMobile(user.getMobile());
        userDO.setBirthday(user.getBirthday());
        userMapper.updateDetailsByPrimaryKeySelective(userDO);
    }

    @Override
    public void update(User user) {
        final Date date = new Date();
        final UserDO userDO = new UserDO();

        userDO.setId(user.getId().getId());
        userDO.setNickName(user.getNickname());
        userDO.setMobile(user.getMobile());
        userDO.setBirthday(user.getBirthday());
        userDO.setLastLoginDate(date);
        userDO.setLastLoginIpv4(user.getLastLoginIp());

        userMapper.updateDetailsByPrimaryKeySelective(userDO);
        userMapper.updateAccessByPrimaryKeySelective(userDO);

    }

    @Override
    public void remove(User user) {

    }

    static {
        ANONYMOUS = new User(AggregateId.of(-1L));
        ANONYMOUS.setStatus(AggregateStatus.INVALID);
    }

}
