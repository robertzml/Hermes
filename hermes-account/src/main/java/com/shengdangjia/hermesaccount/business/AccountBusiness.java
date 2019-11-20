package com.shengdangjia.hermesaccount.business;

import com.shengdangjia.hermesaccount.entity.Account;
import com.shengdangjia.hermesaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class AccountBusiness {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    /**
     * 用户注册
     * @param telephone 电话号码
     * @return
     */
    public String register(String telephone) {
        var token = java.util.UUID.randomUUID().toString();

        // 发送验证码
        String verifyCode = "223344";

        stringRedisTemplate.opsForValue().set("vc_" + token, verifyCode);
        return token;
    }
}
