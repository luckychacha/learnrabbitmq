package com.luckychacha.learnrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leixinxin
 * @date 2019/12/30 11:54 AM
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue directQueue1() {
        return new Queue("directQueue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("directQueue2");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    Binding bindingDirectExchangeError(Queue directQueue1, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("error");
    }

    @Bean
    Binding bindingDirectExchangeInfo(Queue directQueue2, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("info");
    }

    @Bean
    Binding bindingDirectExchangeError2(Queue directQueue2, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("error");
    }

    @Bean
    Binding bindingDirectExchangeWarning(Queue directQueue2, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("warning");
    }
}
