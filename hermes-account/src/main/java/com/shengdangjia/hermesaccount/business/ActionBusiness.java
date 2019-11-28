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

    /**
     * 添加操作记录
     * @param userId
     * @param type
     * @return
     */
    public boolean insert(String userId, short type) {
        try {
            Action action = new Action();
            action.setId(UUID.randomUUID().toString());
            action.setUserId(userId);
            action.setType(type);

            actionRepository.save(action);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
