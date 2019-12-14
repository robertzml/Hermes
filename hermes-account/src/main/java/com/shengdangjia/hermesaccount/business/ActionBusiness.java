package com.shengdangjia.hermesaccount.business;

import com.shengdangjia.hermesaccount.entity.Action;
import com.shengdangjia.hermesaccount.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * 操作记录业务类
 */
@Configuration
public class ActionBusiness {
    @Autowired
    ActionRepository actionRepository;
}
