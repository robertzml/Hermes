package com.shengdangjia.hermesaccount.business;

import com.shengdangjia.common.model.LogMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 日志操作
 */
@Configuration
public class LogUtility {

    @Autowired
    RabbitTemplate rabbitTemplate;

    String module = "hermes-account";

    /**
     * 异常
     * @param action 操作
     * @param message 消息
     */
    public void exception(String action, String message) {
        LogMessage logMessage = new LogMessage(0, module, action, message);
        rabbitTemplate.convertAndSend("LogExchange", "LogRouting", logMessage);
    }

    /**
     * 错误
     * @param action 操作
     * @param message 消息
     */
    public void error(String action, String message) {
        LogMessage logMessage = new LogMessage(1, module, action, message);
        rabbitTemplate.convertAndSend("LogExchange", "LogRouting", logMessage);
    }

    /**
     * 警告
     * @param action 操作
     * @param message 消息
     */
    public void warning(String action, String message) {
        LogMessage logMessage = new LogMessage(2, module, action, message);
        rabbitTemplate.convertAndSend("LogExchange", "LogRouting", logMessage);
    }

    /**
     * 信息
     * @param action 操作
     * @param message 消息
     */
    public void info(String action, String message) {
        LogMessage logMessage = new LogMessage(3, module, action, message);
        rabbitTemplate.convertAndSend("LogExchange", "LogRouting", logMessage);
    }

    /**
     * 调试
     * @param action 操作
     * @param message 消息
     */
    public void debug(String action, String message) {
        LogMessage logMessage = new LogMessage(4, module, action, message);
        rabbitTemplate.convertAndSend("LogExchange", "LogRouting", logMessage);
    }

    /**
     * 详细
     * @param action 操作
     * @param message 消息
     */
    public void verbose(String action, String message) {
        LogMessage logMessage = new LogMessage(5, module, action, message);
        rabbitTemplate.convertAndSend("LogExchange", "LogRouting", logMessage);
    }
}
