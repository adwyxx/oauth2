package com.adwyxx.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Description: Redis数据缓存配置文件
 * @Auther: Leo.W
 * @Date: 2018/11/29 15:11
 */
@Configuration
public class DataStoreConfig {

    public static final String REDIS_CACHE_NAME = "OAUTH_SERVER_REDIS_CACHE";
    public static final String REDIS_PREFIX ="OAUTH_SERVER_REDIS_CACHE_PREFIX";//不为null即可
    public static final Long EXPIRE =60*60L;//缓存有效时间

    /**
    * @description : 用户信息缓存
    * @param cache : Redis Cache
    * @author : Leo.W
    * @date : 2018/11/29 18:01
    * @return :
    **/
    @Bean
    public UserCache userCache(RedisCache cache) throws Exception
    {
        return new SpringCacheBasedUserCache(cache);
    }

    /**
    * @description : 配置AccessToken的存储方式：RedisTokenStore存储
    * @param redisConnectionFactory : Redis工厂类
    * @author : Leo.W
    * @date : 2018/11/29 18:00
    * @return : Token存储方式
    **/
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTokenStore store = new RedisTokenStore(redisConnectionFactory);
        return store;
    }

}
