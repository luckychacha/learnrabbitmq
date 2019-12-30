package com.luckychacha.learnrabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leixinxin
 * @date 2019/12/30 2:17 PM
 */
@Configuration
public class HeadersConfig {

    @Bean
    public Queue headersQueue1() {
        return new Queue("headersQueue1");
    }

    @Bean
    public Queue headersQueue2() {
        return new Queue("headersQueue2");
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange");
    }

    @Bean
    Binding bindingDirectExchange1(Queue headersQueue1, HeadersExchange headersExchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("queueName","headersQueue1");
        map.put("bindType","whereAll");
        return BindingBuilder.bind(headersQueue1).to(headersExchange).whereAll(map).match();
    }

    @Bean
    Binding bindingDirectExchange2(Queue headersQueue2, HeadersExchange headersExchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("queueName","headersQueue2");
        map.put("bindType","whereAny");
        return BindingBuilder.bind(headersQueue2).to(headersExchange).whereAny(map).match();

    }
}
