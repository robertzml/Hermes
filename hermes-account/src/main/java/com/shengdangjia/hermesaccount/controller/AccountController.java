package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.hermesaccount.entity.Account;
import com.shengdangjia.hermesaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/account/list")
    Iterable<Account> getAll() {
        return accountRepository.findAll();
    }
}
