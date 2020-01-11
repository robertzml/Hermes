package com.shengdangjia.hermeslog.receiver;

import com.shengdangjia.common.model.LogMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "LogQueue")
public class LogReceiver {

    @RabbitHandler
    public void process(LogMessage message) {
        System.out.println("Log Queue  : " + message.toString());
    }
}
