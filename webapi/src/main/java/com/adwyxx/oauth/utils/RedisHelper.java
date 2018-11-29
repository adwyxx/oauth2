package com.adwyxx.oauth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description: Redis 操作类
 * @Auther: Leo.W
 * @Date: 2018/11/29 14:23
 */
public class RedisHelper {

    // Redis库操作template
    // 注意：RedisTemplate<K,V>泛型类的实例K、V的数据类型必须一致。如RedisTemplate<Object,Object>，RedisTemplate<String,String>，而不能是RedisTemplate<String,Object>
    @Autowired
    private static RedisTemplate<Object,Object> redisTemplate;

    // String类型的key序列化器
    @Autowired
    public static StringRedisSerializer stringKeySerializer;

    /**
    * @description : 获取RedisTemplate实例
    * @author : Leo.W
    * @date : 2018/11/29 14:56
    * @return : RedisTemplate实例
    **/
    public static RedisTemplate<Object,Object> getTemplate()
    {
        //Redis的Key值默认是序列化成二进制的，为了查看方便将key的序列化方式改变为String
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
    public static void setValue(Object key,Object value)
    {
        getTemplate().opsForValue().set(key,value);
    }

    /**
    * @description : 根据key获取value
    * @param key : Key
    * @author : Leo.W
    * @date : 2018/11/29 14:58
    * @return : Value
    **/
    public static Object getValue(Object key)
    {
       return getTemplate().opsForValue().get(key);
    }

    /**
    * @description : 根据key删除数据
    * @param key : Key
    * @author : Leo.W
    * @date : 2018/11/29 14:59
    **/
    public  static void delete(Object key)
    {
        getTemplate().delete(key);
    }
}
