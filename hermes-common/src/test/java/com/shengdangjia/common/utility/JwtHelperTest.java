package com.shengdangjia.common.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtHelperTest {

    @Test
    void createJWT() {
        var token = JwtHelper.createJWT();

        var dt = JwtHelper.decodeJWT(token);
        System.out.println(dt);
        // assertEquals("token", token);
    }
}