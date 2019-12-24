package com.shengdangjia.common.utility;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RestHelper {

    /**
     * 构造响应返回
     *
     * @param result    相关数据
     * @param errorCode 错误码
     * @param message   消息
     * @return 返回对象
     */
    public static ResponseData makeResponse(Object result, int errorCode, String message) {
        ResponseData data = new ResponseData();
        data.result = result;
        data.errorCode = errorCode;
        data.message = message;

        return data;
    }

    /**
     * 构造响应返回
     *
     * @param result    相关数据
     * @param errorCode 错误码
     * @return 返回内容
     */
    public static ResponseData makeResponse(Object result, ErrorCode errorCode) {
        ResponseData data = new ResponseData();
        data.result = result;
        data.errorCode = errorCode.getCode();
        data.message = errorCode.getErrorMessage();

        try {
            var address = InetAddress.getLocalHost();
            data.ipaddress = address.getHostAddress();
        } catch (UnknownHostException e) {
            data.ipaddress = "unknown";
        }

        return data;
    }

    /**
     * 返回响应内容 JSON 字符串
     * @param data 响应内容
     * @return JSON 字符串
     */
    public static String toJsonString(ResponseData data) {
        var str = JSON.toJSONString(data);
        return str;
    }
}
