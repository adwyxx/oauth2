package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.mapper.UserMapper;
import com.adwyxx.oauth.model.AuthUserDetails;
import com.adwyxx.oauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/29 18:09
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.getUserByName(s);
        if(null==user)
        {
            throw new UsernameNotFoundException("用户名错误");
        }
        else
        {
            return new AuthUserDetails(user);
        }
    }
}
