package com.shengdangjia.common.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDataTest {

    @Test
    void toJsonString() {
        ResponseData data = new ResponseData();
        data.errorCode = 1;
        data.message = "some error";
        data.result = "s";
        data.ipaddress = "192.168.1.1";

        System.out.println(data.toJsonString());
    }
}