package com.shengdangjia.hermesauthentication.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.HermesException;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesauthentication.business.AccountBusiness;
import com.shengdangjia.hermesauthentication.model.LoginConfirmModel;
import com.shengdangjia.hermesauthentication.model.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/account")
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
            var account = this.accountBusiness.findByTelephone(model.telephone);
            if (account == null) {
                return RestHelper.makeResponse(null, ErrorCode.OBJECT_NOT_FOUND);
            }

            if (account.imei.equals(model.imei)) {
                // IMEI 一致
                var token = this.accountBusiness.login(account);
                class Result {
                    public String token;
                }
                var r = new Result();
                r.token = token;

                return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
            } else {
                // IMEI 不一致
                var token = this.accountBusiness.sendVerifyCode(model.telephone);
                class Result {
                    public String token;
                }
                var r = new Result();
                r.token = token;

                return RestHelper.makeResponse(r, 23, "更换登录设备");
            }
        } catch (HermesException e) {
            return RestHelper.makeResponse(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return RestHelper.makeResponse(null, ErrorCode.DATABASE_FAILED);
        }
    }

    /**
     * 用户登录确认
     * @param model 登录确认模型
     * @return id token
     */
    @RequestMapping(value = "/loginConfirm", method = RequestMethod.POST)
    public ResponseData loginConfirm(@RequestBody LoginConfirmModel model) {
        try {
            var token = this.accountBusiness.loginConfirm(model.telephone, model.imei, model.token, model.verifyCode);
            class Result {
                public String token;
            }
            var r = new Result();
            r.token = token;

            return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
        } catch (Exception e) {
            return RestHelper.makeResponse(null, ErrorCode.DATABASE_FAILED);
        }
    }
}
