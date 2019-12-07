package com.shengdangjia.hermesauthentication.controller;

import com.shengdangjia.common.model.ErrorCode;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    public void login(String telephone, String imei) {
        ErrorCode c = ErrorCode.DATABASE_FAILED;
        return;
    }
}
