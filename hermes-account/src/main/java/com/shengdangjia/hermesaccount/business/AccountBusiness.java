package com.shengdangjia.hermesaccount.business;

import com.shengdangjia.common.utility.SMSHelper;
import com.shengdangjia.hermesaccount.entity.Account;
import com.shengdangjia.hermesaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

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
     * 按手机号查询用户
     * @param telephone 手机号
     * @return
     */
    public Account findByTelephone(String telephone) {
        return accountRepository.findByTelephone(telephone);
    }

    /**
     * 用户注册申请
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

        stringRedisTemplate.opsForValue().set("vc_" + token, telephone + verifyCode, Duration.ofSeconds(180));
        return token;
    }

    /**
     * 添加用户
     * @param telephone 手机
     * @param imei IMEI
     * @param token 验证令牌
     * @param verifyCode 验证码
     * @return
     */
    public boolean create(String telephone, String imei, String token, String verifyCode) {
        // 比较验证码
        var vc = stringRedisTemplate.opsForValue().get("vc_" + token);
        if (vc == null || !vc.equals(telephone + verifyCode))
            return false;

        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setTelephone(telephone);
        account.setImei(imei);
        account.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        account.setStatus(0);

        var t = accountRepository.save(account);
        return true;
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
