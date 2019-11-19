package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.hermesaccount.business.AccountBusiness;
import com.shengdangjia.hermesaccount.entity.Account;
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

    @RequestMapping("/account/register")
    boolean register(@RequestParam(value = "token")String token) {
        return accountBusiness.register(token);
    }
}
