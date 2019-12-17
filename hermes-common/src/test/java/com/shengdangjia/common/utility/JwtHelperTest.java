package com.shengdangjia.common.utility;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.lang.Thread.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtHelperTest {

    @Test
    void testIdJWT() throws InterruptedException {
        var token = JwtHelper.createIdJWT("123");

        System.out.println(token);
        sleep(2000);
        var res = JwtHelper.decodeIdJWT(token);
        assertEquals(true, res.isSuccess());
        assertEquals("123", res.getUid());
    }

    @Test
    void testIdToken() throws InterruptedException {
        var token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJpZCB0b2tlbiIsInVpZCI6IjgzNzM4YjVlLTZiNWYtNGM3YS1iY2QyLTM2YmY4Nzc5NmQ4NSIsImlzcyI6ImF1dGgiLCJleHAiOjE1NzY1ODQ4MTd9.hbf1ojCBUBQqNUcG7ExPhbR9rcg7X0TurH_w2l_GwQUmfIZaf20ORDLKp0l8Z0gm";

        var jwt = JwtHelper.decodeIdJWT(token);

        assertEquals("83738b5e-6b5f-4c7a-bcd2-36bf87796d85", jwt.getUid());
    }
}