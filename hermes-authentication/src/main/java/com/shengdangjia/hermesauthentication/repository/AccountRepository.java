package com.shengdangjia.hermesauthentication.repository;

import com.shengdangjia.hermesauthentication.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    public Account findByTelephone(String telephone);
}
