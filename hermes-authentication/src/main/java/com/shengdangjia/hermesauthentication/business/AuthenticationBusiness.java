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
     * @return 0:验证通过  1:超时  2:失败  3:不存在
     */
    public int authId(String token) {
        // 验证jwt
        var jwt = JwtHelper.decodeIdJWT(token);
        if (!jwt.isSuccess()) {
            if (jwt.isExpire()) {
                return 1;
            } else {
                return 2;
            }
        }

        // 查找缓存中id token 是否存在
        var cache = stringRedisTemplate.opsForValue().get("id_" + jwt.getUid());
        if (cache == null || !cache.equals(token))
            return 3;

        return 0;
    }

    /**
     * 生成access token
     * @param idToken 原id token
     * @return access token
     */
    public String generateAccess(String idToken) {
        var jwt = JwtHelper.decodeIdJWT(idToken);

        String token = JwtHelper.createAccessJWT(jwt.getUid());
        return token;
    }

    public boolean testAuth(int a) {
        if (a == 1) return true;
        else return  false;
    }
}
