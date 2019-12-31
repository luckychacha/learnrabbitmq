package com.luckychacha.learnrabbitmq.config;

import com.luckychacha.learnrabbitmq.rpc.RpcSender;
import com.luckychacha.learnrabbitmq.rpc.RpcServer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Leixinxin
 * @date 2019/12/30 5:15 PM
 */
@Configuration
public class RpcConfig {

        @Bean
        public DirectExchange exchange() {
            return new DirectExchange("tut.rpc");
        }

        @Bean
        public Queue queue() {
            return new Queue("tut.rpc.requests");
        }

        @Bean
        public Binding binding(DirectExchange exchange,
                               Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with("rpc");
        }

        @Bean
        public RpcServer server() {
            return new RpcServer();
        }

}