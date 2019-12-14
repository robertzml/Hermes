package com.shengdangjia.hermesauthentication.business;

import com.shengdangjia.hermesauthentication.entity.Action;
import com.shengdangjia.hermesauthentication.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * 操作记录业务类
 */
@Configuration
public class ActionBusiness {
    @Autowired
    ActionRepository actionRepository;

    /**
     * 添加操作记录
     *
     * @param userId 用户ID
     * @param type   操作类型
     * @return
     */
    public void insert(String userId, short type, String parameter1) {
        Action action = new Action();
        action.id = UUID.randomUUID().toString();
        action.userId = userId;
        action.type = type;
        action.logTime = new Timestamp(System.currentTimeMillis());
        action.parameter1 = parameter1;

        actionRepository.save(action);
        return;
    }
}
