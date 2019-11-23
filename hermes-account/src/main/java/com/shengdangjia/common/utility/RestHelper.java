package com.shengdangjia.common.utility;

import com.shengdangjia.common.model.ResponseData;

public class RestHelper {

    /**
     * 构造响应返回
     * @param result 相关数据
     * @param errorCode 错误码
     * @return 返回对象
     */
    public static ResponseData makeResponse(Object result, int errorCode) {
        ResponseData data = new ResponseData();
        data.result = result;
        data.errorCode = errorCode;

        if (errorCode == 0) {
            data.message = "ok";
        } else {
            data.message = "some error";
        }

        return data;
    }
}
