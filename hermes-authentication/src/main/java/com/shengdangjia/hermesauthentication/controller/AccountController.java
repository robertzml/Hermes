package com.shengdangjia.hermesauthentication.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.HermesException;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesauthentication.business.AccountBusiness;
import com.shengdangjia.hermesauthentication.model.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
public class AccountController {
    @Autowired
    AccountBusiness accountBusiness;

    /**
     * 用户登录
     *
     * @param model 登录参数
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody LoginModel model) {
        try {
            this.accountBusiness.login(model.telephone, model.imei);

            return RestHelper.makeResponse(null, ErrorCode.SUCCESS);
        } catch (HermesException e) {
            return RestHelper.makeResponse(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return RestHelper.makeResponse(null, ErrorCode.DATABASE_FAILED);
        }
    }
}
