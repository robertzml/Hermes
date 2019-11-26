package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesaccount.business.AccountBusiness;
import com.shengdangjia.hermesaccount.entity.*;
import com.shengdangjia.common.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountBusiness accountBusiness;

    @RequestMapping("/account/list")
    Iterable<Account> getAll() {
        return accountBusiness.findAll();
    }

    @RequestMapping("/account/find")
    Account find(@RequestParam(value = "telephone") String telephone) {
        return accountBusiness.findByTelephone(telephone);
    }

    /**
     * 用户注册接口
     * @param telephone 电话号码
     * @return token
     */
    @RequestMapping("/account/register")
    ResponseData register(@RequestParam(value = "telephone") String telephone) {
        try {
            // 检查手机号是否存在
            var account = accountBusiness.findByTelephone(telephone);
            if (account != null) {
                return RestHelper.makeResponse(null, 1);
            }

            var token = accountBusiness.register(telephone);

            class Result {
                public String token;
            }
            var r = new Result();
            r.token = token;

            return RestHelper.makeResponse(r, 0);
        }
        catch (Exception e) {
            return RestHelper.makeResponse(null, 1);
        }
    }

    /**
     * 注册确认接口
     * @param model 确认参数
     * @return
     */
    @RequestMapping(value = "/account/registerConfirm", method = RequestMethod.POST)
    ResponseData registerConfirm(@RequestBody RegisterConfirmModel model) {
        var result = accountBusiness.create(model.telephone, model.imei, model.token, model.verifyCode);

        if (result)
            return RestHelper.makeResponse(null, 0);
        else
            return RestHelper.makeResponse(null, 1);
    }
}
