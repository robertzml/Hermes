package com.shengdangjia.hermesaccount.business;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.HermesException;
import com.shengdangjia.common.utility.SMSHelper;
import com.shengdangjia.hermesaccount.entity.Account;
import com.shengdangjia.hermesaccount.entity.Action;
import com.shengdangjia.hermesaccount.repository.AccountRepository;
import com.shengdangjia.hermesaccount.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

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
    ActionRepository actionRepository;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    /**
     * 按手机号查询用户
     *
     * @param telephone 手机号
     * @return
     */
    public Account findByTelephone(String telephone) {
        return accountRepository.findByTelephone(telephone);
    }

    /**
     * 用户注册申请
     *
     * @param telephone 电话号码
     * @return token 验证码token
     */
    public String register(String telephone) throws HermesException {
        // 检查手机号是否存在
        var account = findByTelephone(telephone);
        if (account != null) {
            throw new HermesException(20, "手机号已存在");
        }

        // 发送验证码
        String verifyCode = generateCode();
        var result = SMSHelper.sendVerifyCode(telephone, verifyCode);
        if (!result) {
            throw new HermesException(21, "发送验证码失败");
        }
        var token = java.util.UUID.randomUUID().toString();
        // 验证码有效期10分钟
        stringRedisTemplate.opsForValue().set("vc_" + token, telephone + verifyCode, Duration.ofSeconds(10 * 60));
        return token;
    }

    /**
     * 添加用户
     *
     * @param telephone  手机
     * @param imei       IMEI
     * @param token      验证令牌
     * @param verifyCode 验证码
     */
    @Transactional(rollbackFor = {HermesException.class, RuntimeException.class, Error.class})
    public void create(String telephone, String imei, String token, String verifyCode) throws HermesException {
        // 比较验证码
        var vc = stringRedisTemplate.opsForValue().get("vc_" + token);
        if (vc == null || !vc.equals(telephone + verifyCode))
            throw new HermesException(22, "验证码错误");

        Account account = new Account();
        var uid = UUID.randomUUID().toString();
        account.setId(uid);
        account.setTelephone(telephone);
        account.setImei(imei);
        account.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        account.setStatus(0);
        var t = accountRepository.save(account);

        Action action = new Action();
        action.setId(UUID.randomUUID().toString());
        action.setUserId(uid);
        action.setType((short) 1);
        action.setLogTime(new Timestamp(System.currentTimeMillis()));
        action.setParameter1(imei);
        actionRepository.save(action);
    }

    /**
     * 生成手机验证码
     *
     * @return 六位验证码
     */
    private String generateCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
