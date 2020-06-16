package com.lgy.shiro.service.impl;

import com.lgy.shiro.mapper.USerMapper;
import com.lgy.shiro.pojo.User;
import com.lgy.shiro.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    USerMapper userMapper;
    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
