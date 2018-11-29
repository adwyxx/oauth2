package com.adwyxx.oauth.mapper;

import com.adwyxx.oauth.model.AccessToken;

public interface AccessTokenMapper {
    int deleteById(Long id);

    int deleteByToken(String token);

    int insert(AccessToken record);

    AccessToken selectById(Long id);

    AccessToken selectByToken(String token);

    int updateById(AccessToken record);

    int updateRefreshToken(AccessToken record);
}