package com.shengdangjia.common.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

/**
 * JWT 工具类
 */
public class JwtHelper {

    public static String createJWT() {
        Algorithm algorithm = Algorithm.HMAC384("hermes");
        var ca = Calendar.getInstance();
        ca.add(Calendar.HOUR, 1);
        var dt = ca.getTime();
        String token = JWT.create().withIssuer("account").withExpiresAt(dt).sign(algorithm);

        return token;
    }

    public static Date decodeJWT(String token) {
        DecodedJWT jwt = JWT.decode(token);
        var dt = jwt.getExpiresAt();
        return dt;
    }
}
