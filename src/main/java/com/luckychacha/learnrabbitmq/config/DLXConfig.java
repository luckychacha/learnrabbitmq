package com.luckychacha.learnrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leixinxin
 * @date 2019/12/30 11:54 AM
 */
@Configuration
public class DLXConfig {

    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", "delayProcessExchange");
        params.put("x-dead-letter-routing-key", "trans");
        return new Queue("delayProcessQueue", true, false, false, params);
    }

    @Bean
    public Queue dlxQueue1() {
        return new Queue("dlxQueue1");
    }
    @Bean
    public Queue transQueue() {
        return new Queue("transQueue");
    }

    @Bean
    DirectExchange delayProcessExchange() {
        return new DirectExchange("delayProcessExchange", true, false);
    }

    @Bean
    Binding dlxBinding(Queue delayProcessQueue, DirectExchange delayProcessExchange) {
        return BindingBuilder.bind(delayProcessQueue).to(delayProcessExchange).with("dlx");
    }
    @Bean
    Binding dlxQueue1Binding(Queue dlxQueue1, DirectExchange delayProcessExchange) {
        return BindingBuilder.bind(dlxQueue1).to(delayProcessExchange).with("queue1");
    }
    @Bean
    Binding transQueueBinding(Queue transQueue, DirectExchange delayProcessExchange) {
        return BindingBuilder.bind(transQueue).to(delayProcessExchange).with("trans");
    }
}
