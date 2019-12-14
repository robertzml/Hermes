package com.shengdangjia.hermesauthentication.business;

import com.shengdangjia.common.model.HermesException;
import com.shengdangjia.common.utility.JwtHelper;
import com.shengdangjia.common.utility.SMSHelper;
import com.shengdangjia.hermesauthentication.entity.Account;
import com.shengdangjia.hermesauthentication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
public class AccountBusiness {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ActionBusiness actionBusiness;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
     * 发送验证码
     * 登录imei 不一致时发送
     *
     * @param telephone 手机号
     * @return 验证码 token
     * @throws HermesException
     */
    public String sendVerifyCode(String telephone) throws HermesException {
        String verifyCode = SMSHelper.generateCode();

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
     * 用户登录
     *
     * @param account 用户信息
     * @return id token
     * @throws HermesException
     */
    public String login(Account account) throws HermesException {
        // 保存操作记录
        this.actionBusiness.insert(account.id, (short) 2, account.imei);

        var token = JwtHelper.createIdJWT(account.id);

        // 令牌1小时过期
        stringRedisTemplate.opsForValue().set("id_" + account.id, token, Duration.ofHours(1));

        return token;
    }
}
