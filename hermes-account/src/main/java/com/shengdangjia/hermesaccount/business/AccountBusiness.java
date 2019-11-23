package com.shengdangjia.hermesaccount.business;

import com.shengdangjia.common.utility.SMSHelper;
import com.shengdangjia.hermesaccount.entity.Account;
import com.shengdangjia.hermesaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Random;

/**
 * 账户业务类
 */
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
    public String register(String telephone) throws Exception {
        var token = java.util.UUID.randomUUID().toString();

        // 发送验证码
        String verifyCode = generateCode();
        var result = SMSHelper.sendVerifyCode(telephone, verifyCode);
        if (!result) {
            throw new Exception("发送失败");
        }

        stringRedisTemplate.opsForValue().set("vc_" + token, verifyCode, Duration.ofSeconds(180));
        return token;
    }

    /**
     * 生成手机验证码
     * @return 六位验证码
     */
    private String generateCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(String.valueOf(random.nextInt(10)));
        }
        return code.toString();
    }
}
