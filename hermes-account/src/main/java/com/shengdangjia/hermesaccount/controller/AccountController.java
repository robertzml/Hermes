package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesaccount.business.AccountBusiness;
import com.shengdangjia.hermesaccount.entity.Account;
import com.shengdangjia.common.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountBusiness accountBusiness;

    @RequestMapping("/account/list")
    Iterable<Account> getAll() {
        return accountBusiness.findAll();
    }

    /**
     * 用户注册
     *
     * @param telephone 电话号码
     * @return token
     */
    @RequestMapping("/account/register")
    ResponseData register(@RequestParam(value = "telephone") String telephone) {
        try {
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
}
