package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import com.shengdangjia.hermesaccount.business.AccountBusiness;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RequestMapping(value = "/account")
@RestController
public class AccountController {
    @Autowired
    AccountBusiness accountBusiness;

    @Autowired
    HttpServletRequest request;

    @Autowired
    RabbitTemplate rabbitTemplate;

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

        Map<String, Object> map = new HashMap<>();
        map.put("messageId", UUID.randomUUID().toString());
        map.put("telephone", telephone);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);

        return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
    }
}
