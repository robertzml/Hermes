package com.shengdangjia.hermesaccount.repository;

import com.shengdangjia.hermesaccount.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
}
