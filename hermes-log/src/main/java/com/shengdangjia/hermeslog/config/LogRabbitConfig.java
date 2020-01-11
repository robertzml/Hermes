package com.shengdangjia.hermeslog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogRabbitConfig {

    /**
     * 日志队列
     *
     * @return
     */
    @Bean
    public Queue logQueue() {
        return new Queue("LogQueue", true);
    }

    /**
     * 日志交换机
     *
     * @return
     */
    @Bean
    public DirectExchange logExchange() {
        return new DirectExchange("LogExchange");
    }

    @Bean
    Binding bindingExchange() {
        return BindingBuilder.bind(logQueue()).to(logExchange()).with("LogRouting");
    }
}
