package com.shengdangjia.hermeslog.receiver;

import com.shengdangjia.common.model.LogMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "topic.log")
public class TopicLogReceiver {

    @RabbitHandler
    public void process(LogMessage message) {

        // System.out.println(message.getClass());
        System.out.println("TopicLogReceiver消费者收到消息  : " + message.toString());
    }
}
