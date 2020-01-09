package com.shengdangjia.hermeslog.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.log")
public class TopicLogReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicLogReceiver消费者收到消息  : " + testMessage.toString());
    }
}
