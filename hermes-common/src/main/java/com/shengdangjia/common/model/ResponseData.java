package com.shengdangjia.common.model;

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
}

