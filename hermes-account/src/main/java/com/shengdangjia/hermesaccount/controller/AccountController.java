package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesaccount.business.AccountBusiness;
import com.shengdangjia.hermesaccount.business.LogUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RequestMapping(value = "/account")
@RestController
public class AccountController {
    @Autowired
    AccountBusiness accountBusiness;

    @Autowired
    LogUtility logUtility;

    @Autowired
    HttpServletRequest request;

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping("/list")
    ResponseData getAll() {
        var access = request.getHeader("Authorization");
        System.out.println(access);

        var r = accountBusiness.findAll();
        return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
    }

    /**
     * 获取用户信息
     *
     * @param telephone 手机号
     * @return 用户信息
     */
    @RequestMapping("/find")
    ResponseData find(@RequestParam(value = "telephone") String telephone) {
        var r = accountBusiness.findByTelephone(telephone);

        logUtility.verbose("find user", "find user by " + telephone);
        return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
    }
}
