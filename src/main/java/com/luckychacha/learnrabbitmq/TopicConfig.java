package com.luckychacha.learnrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leixinxin
 * @date 2019/12/30 11:57 AM
 */
@Configuration
public class TopicConfig {

    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue2", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingTopicExchangeOrange(Queue topicQueue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("*.orange.*");
    }

    @Bean
    Binding bindingTopicExchangeRabbit(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("*.*.rabbit");
    }

    @Bean
    Binding bindingTopicExchangeLazy(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("lazy.#");
    }
}
