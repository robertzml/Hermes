package com.shengdangjia.hermesauthentication.business;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.HermesException;
import com.shengdangjia.hermesauthentication.entity.Action;
import com.shengdangjia.hermesauthentication.repository.AccountRepository;
import com.shengdangjia.hermesauthentication.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.UUID;

@Configuration
public class AccountBusiness {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ActionRepository actionRepository;

    /**
     * 用户登录
     * @param telephone 手机号
     * @param imei IMEI
     * @throws HermesException
     */
    public void login(String telephone, String imei) throws HermesException {
        var account = this.accountRepository.findByTelephone(telephone);
        if (account == null) {
            throw new HermesException(ErrorCode.OBJECT_NOT_FOUND);
        }

        if (!account.imei.equals(imei)) {
            throw new HermesException(23, "更换登录设备");
        }

        Action action = new Action();
        action.id = UUID.randomUUID().toString();
        action.userId = account.id;
        action.type = (short)2;
        action.logTime = new Timestamp(System.currentTimeMillis());
        action.parameter1 = imei;
        actionRepository.save(action);
    }
}
