package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.HermesException;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesaccount.business.AccountBusiness;
import com.shengdangjia.hermesaccount.model.RegisterConfirmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册控制器
 */
@RequestMapping(value = "/register")
@RestController
public class RegisterController {
    @Autowired
    AccountBusiness accountBusiness;

    /**
     * 用户注册接口
     *
     * @param telephone 电话号码
     * @return token
     */
    @RequestMapping("/signUp")
    ResponseData signUp(@RequestParam(value = "telephone") String telephone) {
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
    @RequestMapping(value = "/signUpConfirm", method = RequestMethod.POST)
    ResponseData signUpConfirm(@RequestBody RegisterConfirmModel model) {
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
