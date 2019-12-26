package com.shengdangjia.hermesauthentication.business;

import com.shengdangjia.common.utility.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 认证业务类
 */
@Configuration
public class AuthenticationBusiness {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * id token 验证
     * @param token id token
     * @return
     */
    public boolean authId(String token) {
        // 验证jwt
        var jwt = JwtHelper.decodeIdJWT(token);
        if (!jwt.isSuccess()) {
            return false;
        }

        // 查找缓存中id token 是否存在
        var cache = stringRedisTemplate.opsForValue().get("id_" + jwt.getUid());
        if (cache == null || !cache.equals(token))
            return false;

        return true;
    }

    public boolean testAuth(int a) {
        if (a == 1) return true;
        else return  false;
    }
}
