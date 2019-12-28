package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.HermesException;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesaccount.business.AccountBusiness;
import com.shengdangjia.hermesaccount.model.RegisterConfirmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RequestMapping(value = "/account")
@RestController
public class AccountController {
    @Autowired
    AccountBusiness accountBusiness;

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
        return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
    }

    /**
     * 用户注册接口
     *
     * @param telephone 电话号码
     * @return token
     */
    @RequestMapping("/register")
    ResponseData register(@RequestParam(value = "telephone") String telephone) {
        try {
            var token = accountBusiness.register(telephone);

            class Result {
                public String token;
            }
            var r = new Result();
            r.token = token;

            return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
        } catch (HermesException e) {
            return RestHelper.makeResponse(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return RestHelper.makeResponse(null, ErrorCode.DATABASE_FAILED);
        }
    }

    /**
     * 注册确认接口
     *
     * @param model 确认参数
     * @return 
     */
    @RequestMapping(value = "/registerConfirm", method = RequestMethod.POST)
    ResponseData registerConfirm(@RequestBody RegisterConfirmModel model) {
        try {
            accountBusiness.create(model.telephone, model.imei, model.token, model.verifyCode);
            return RestHelper.makeResponse(null, ErrorCode.SUCCESS);
        } catch (HermesException e) {
            return RestHelper.makeResponse(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return RestHelper.makeResponse(null, ErrorCode.DATABASE_FAILED);
        }
    }
}
