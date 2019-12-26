package com.shengdangjia.hermesgateway.filter;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdTokenFilterTest {

    @Test
    void filter() {
        ResponseData d = RestHelper.makeResponse("无验证", ErrorCode.ERROR);
        System.out.println(d.toJsonString());
    }
}