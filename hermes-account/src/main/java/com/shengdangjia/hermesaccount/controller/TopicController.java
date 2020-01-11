package com.shengdangjia.hermesaccount.controller;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.LogMessage;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/topic")
@RestController
public class TopicController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/log")
    ResponseData log() {
        LogMessage message = new LogMessage(2, "hermes-accout", "topic", "test the topic");

        rabbitTemplate.convertAndSend("topicExchange", "topic.log", message);

        String r = "log success";
        return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
    }

    @RequestMapping("/event")
    ResponseData event() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: event";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.event", womanMap);

        String r = "event success";
        return RestHelper.makeResponse(r, ErrorCode.SUCCESS);
    }
}
