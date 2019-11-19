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

    public boolean register(String token) {

        stringRedisTemplate.opsForValue().set(token, "223344");
        return true;
    }
}
