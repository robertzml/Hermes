package com.shengdangjia.hermeslog.receiver;

import com.shengdangjia.common.model.LogMessage;
import com.shengdangjia.hermeslog.entity.Log;
import com.shengdangjia.hermeslog.repository.LogRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
@RabbitListener(queues = "LogQueue")
public class LogReceiver {

    @Autowired
    LogRepository logRepository;


    @RabbitHandler
    public void process(LogMessage message) {
        System.out.println("Log Queue  : " + message.toString());

        try {
            Log log = new Log();
            log.setId(UUID.randomUUID().toString());
            log.setLevel(message.getLevel());
            log.setModule(message.getModule());
            log.setAction(message.getAction());
            log.setMessage(message.getMessage());
            log.setTimestamp(new Timestamp(message.getTimestamp()));

            logRepository.save(log);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
