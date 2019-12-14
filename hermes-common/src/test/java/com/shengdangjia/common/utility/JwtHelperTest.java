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
}