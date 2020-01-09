package com.shengdangjia.hermesaccount.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    public final static String logQ = "topic.log";

    public final static String eventQ = "topic.event";

    @Bean
    public Queue logQueue() {
        return new Queue(TopicRabbitConfig.logQ);
    }

    @Bean
    public Queue eventQueue() {
        return new Queue(TopicRabbitConfig.eventQ);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingLogExchange() {
        return BindingBuilder.bind(logQueue()).to(exchange()).with(logQ);
    }

    @Bean
    Binding bindingEventExchange() {
        return BindingBuilder.bind(eventQueue()).to(exchange()).with("topic.#");
    }
}
