package com.shengdangjia.hermesauthentication.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 认证业务类
 */
public class AuthenticationBusiness {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public boolean auth(String token) {
        return true;
    }
}
