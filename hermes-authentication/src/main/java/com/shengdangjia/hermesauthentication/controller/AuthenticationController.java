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
     * 成功后返回 access token
     *
     * @param token id token
     * @return
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public ResponseData id(String token) {
        System.out.println("auth id token: " + token);
        var result = this.authenticationBusiness.authId(token);
        switch (result) {
            case 0:
                String access = this.authenticationBusiness.generateAccess(token);
                return RestHelper.makeResponse(access, ErrorCode.SUCCESS);
            case 1:
                return RestHelper.makeResponse(null, ErrorCode.AUTHORIZATION_EXPIRE);
            default:
                return RestHelper.makeResponse(null, ErrorCode.AUTHORIZATION_FAILED);
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
