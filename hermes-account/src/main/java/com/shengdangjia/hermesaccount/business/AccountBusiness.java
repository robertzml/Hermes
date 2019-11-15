package com.shengdangjia.hermesaccount.business;

import com.shengdangjia.hermesaccount.entity.Account;
import com.shengdangjia.hermesaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBusiness {
    @Autowired
    AccountRepository accountRepository;

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }
}
