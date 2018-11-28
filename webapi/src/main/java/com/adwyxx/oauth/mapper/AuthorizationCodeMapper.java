package com.adwyxx.oauth.mapper;

import com.adwyxx.oauth.model.AuthorizationCode;

public interface AuthorizationCodeMapper {
    int deleteByPrimaryKey(String code);

    int insert(AuthorizationCode record);

    int insertSelective(AuthorizationCode record);

    AuthorizationCode selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(AuthorizationCode record);

    int updateByPrimaryKey(AuthorizationCode record);
}