package com.adwyxx.oauth.mapper;

import com.adwyxx.oauth.model.AuthorizationClient;

public interface AuthorizationClientMapper {
    int deleteByPrimaryKey(String clientId);

    int insert(AuthorizationClient record);

    int insertSelective(AuthorizationClient record);

    AuthorizationClient selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(AuthorizationClient record);

    int updateByPrimaryKey(AuthorizationClient record);
}