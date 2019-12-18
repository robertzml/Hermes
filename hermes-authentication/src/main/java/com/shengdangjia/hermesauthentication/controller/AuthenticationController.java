package com.shengdangjia.hermesauthentication.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesauthentication.business.AuthenticationBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationBusiness authenticationBusiness;

    /**
     * 用户认证
     * 认证 id token 是否有效
     *
     * @param token id token
     * @return
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseData auth(String token) {
        var result = this.authenticationBusiness.auth(token);
        if (result) {
            return RestHelper.makeResponse(null, ErrorCode.SUCCESS);
        } else {
            return RestHelper.makeResponse(null, ErrorCode.ERROR);
        }
    }

    @RequestMapping(value = "/testauth", method = RequestMethod.GET)
    public ResponseData testAuth(int a) {
        var result = this.authenticationBusiness.testAuth(a);
        if (result) {
            return RestHelper.makeResponse(null, ErrorCode.SUCCESS);
        } else {
            return RestHelper.makeResponse(null, ErrorCode.ERROR);
        }
    }
}
