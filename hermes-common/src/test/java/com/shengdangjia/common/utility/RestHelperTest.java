package com.shengdangjia.common.utility;

import com.shengdangjia.common.model.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestHelperTest {

    @Test
    void toJsonString() {
        var d = RestHelper.makeResponse("无验证", ErrorCode.ERROR);
        var s = RestHelper.toJsonString(d);
        System.out.println(s);
    }
}