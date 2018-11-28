package com.adwyxx.oauth.mapper;

import com.adwyxx.oauth.model.AccessToken;
import com.adwyxx.oauth.model.AccessTokenWithBLOBs;

public interface AccessTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccessTokenWithBLOBs record);

    int insertSelective(AccessTokenWithBLOBs record);

    AccessTokenWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccessTokenWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AccessTokenWithBLOBs record);

    int updateByPrimaryKey(AccessToken record);
}