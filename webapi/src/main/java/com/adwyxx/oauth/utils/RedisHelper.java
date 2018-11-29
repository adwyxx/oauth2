package com.adwyxx.oauth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Description: Redis 操作类
 * @Auther: Leo.W
 * @Date: 2018/11/29 14:23
 */
public class RedisHelper {

    // Redis库操作template
    @Autowired
    private static RedisTemplate<String,Object> redisTemplate;

    // String类型的key序列化器
    @Autowired
    public static RedisSerializer<String> stringKeySerializer;

    /**
    * @description : 获取RedisTemplate实例
    * @author : Leo.W
    * @date : 2018/11/29 14:56
    * @return : RedisTemplate实例
    **/
    public static RedisTemplate<String,Object> getTemplate()
    {
        if(redisTemplate.getKeySerializer().getClass().equals(redisTemplate.getDefaultSerializer().getClass()))
        {
            redisTemplate.setKeySerializer(stringKeySerializer);
        }

        return redisTemplate;
    }

    /**
    * @description : 设置key value
    * @param  key: Key
    * @param value : Value
    * @author : Leo.W
    * @date : 2018/11/29 14:57
    **/
    public static void setValue(String key,Object value)
    {
        getTemplate().boundValueOps(key).set(value);
    }

    /**
    * @description : 根据key获取value
    * @param key : Key
    * @author : Leo.W
    * @date : 2018/11/29 14:58
    * @return : Value
    **/
    public static Object getValue(String key)
    {
       return getTemplate().boundValueOps(key).get();
    }

    /**
    * @description : 根据key删除数据
    * @param key : Key
    * @author : Leo.W
    * @date : 2018/11/29 14:59
    **/
    public  static void delete(String key)
    {
        getTemplate().delete(key);
    }
}
