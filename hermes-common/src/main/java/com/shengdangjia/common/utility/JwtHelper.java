package com.shengdangjia.common.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shengdangjia.common.model.JwtState;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JWT 工具类
 */
public class JwtHelper {
    private static String key = "hermes";

    /**
     * 创建 id token
     * @param uid 用户GUID
     * @return token
     */
    public static String createIdJWT(String uid) {
        Algorithm algorithm = Algorithm.HMAC384(key);

        var ldt = LocalDateTime.now().plusHours(1);
        var dt = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        String token = JWT.create()
                .withIssuer("auth")
                .withExpiresAt(dt)
                .withSubject("id token")
                .withClaim("uid", uid)
                .sign(algorithm);

        return token;
    }

    /**
     * 验证id token
     * @param token 令牌
     * @return JWT数据
     */
    public static JwtState decodeIdJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC384(key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth")
                    .withSubject("id token")
                    .acceptExpiresAt(1)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            var dt = jwt.getExpiresAt();
            LocalDateTime ldt = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            String uid = jwt.getClaim("uid").asString();

            JwtState state = new JwtState();
            state.setSuccess(true);
            state.setExpire(ldt);
            state.setUid(uid);

            return state;
        } catch (JWTVerificationException e) {
            JwtState state = new JwtState();
            state.setSuccess(false);
            return state;
        }
    }
}
