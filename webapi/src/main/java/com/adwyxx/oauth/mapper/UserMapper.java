package com.adwyxx.oauth.mapper;

import com.adwyxx.oauth.model.User;

public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);

    User getUserById(Integer id);

    int deleteUserById(Integer id);

    User getUserByName(String username);
}