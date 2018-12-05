package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.mapper.UserMapper;
import com.adwyxx.oauth.model.User;
import com.adwyxx.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }


}
