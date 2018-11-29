package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.mapper.UserMapper;
import com.adwyxx.oauth.model.User;
import com.adwyxx.oauth.service.UserService;
import com.adwyxx.oauth.utils.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {

        Object user = RedisHelper.getValue(id);
        //双重检验所，防止高并发下Redis穿透
        if(null==user)
        {
            //加锁，如果高并发时同时有多个线程访问到此处，则执行需一个线程进入下面代码，其他线程等待
            //这样，如果进入下面代码中的进程从数据库中取到了值，并保存到Redis中，后面的线程将不会再从数据库中读取数据了
            synchronized (this)
            {
                //再次检验Redis中是否有值
                user = RedisHelper.getValue(id);
                if(null==user)
                {
                    //如果Redis中没有值，则从数据库中读取
                    user = userMapper.getUserById(id);

                    if(null!=user)
                    {
                        RedisHelper.setValue(id,user);
                    }
                }
            }
        }

        return null==user?null:(User)user;
    }
}
