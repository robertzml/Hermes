package com.shengdangjia.common.model;

import com.alibaba.fastjson.JSON;

/**
 * 通用返回数据类
 */
public class ResponseData {
    /**
     * 返回数据
     */
    public Object result;

    /**
     * 错误码
     */
    public int errorCode;

    /**
     * 消息
     */
    public String message;

    /**
     * 服务端ip
     */
    public String ipaddress;

    /**
     * 返回响应内容 JSON 字符串
     * @return JSON 字符串
     */
    public String toJsonString() {
        var str = JSON.toJSONString(this);
        return str;
    }

    public static ResponseData fromJsonString(String str) {
        ResponseData data = JSON.parseObject(str, ResponseData.class);
        return data;
    }
}