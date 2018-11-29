package com.adwyxx.oauth.service;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/29 09:53
 */
public interface AuthorizationService {

    /**
    * @description : 根据用户id和客户端应用id生成Authorization Code
    * @param userId : 用户id
    * @param clientId : 客户端应用id
    * @author : Leo.W
    * @date : 2018/11/29 9:57
    * @return : Authorization Code
    **/
    public String generatAuthCode(Integer userId,String clientId);

}
