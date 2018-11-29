package com.adwyxx.oauth.mapper;

import com.adwyxx.oauth.model.OAuthClient;

public interface OAuthClientMapper {
    int deleteByPrimaryKey(String clientId);

    int insert(OAuthClient record);

    int insertSelective(OAuthClient record);

    OAuthClient selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(OAuthClient record);

    int updateByPrimaryKey(OAuthClient record);
}