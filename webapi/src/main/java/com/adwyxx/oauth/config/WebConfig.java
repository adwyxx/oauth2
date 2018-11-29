package com.adwyxx.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/29 10:37
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * @description : 全局跨域配置
     * @param registry :
     * @author : Leo.W
     * @date : 2018/11/20 17:11
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //跨域请求HTTP路由
                .allowedOrigins("*") //允许跨域请求的域名，多个都好分割
                .allowedMethods("*") //允许跨域请求的Http Method，多个都好分割，如:"GET","POST"
                .allowedHeaders("*") //允许跨域请求的Http Header，多个都好分割，如:"Authorization","Accpet"
                .allowCredentials(true) //是否允许证书 不再默认开启
                .maxAge(3600);
    }
}
