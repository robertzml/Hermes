package com.shengdangjia.hermesauthentication.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    /**
     * 用户认证
     * 认证 id token 是否有效
     * @param token id token
     * @return
     */
    public ResponseData auth(String token) {
        if (token.length() > 10) {
            return RestHelper.makeResponse(null, ErrorCode.SUCCESS);
        } else {
            return RestHelper.makeResponse(null, ErrorCode.ERROR);
        }
    }
}
