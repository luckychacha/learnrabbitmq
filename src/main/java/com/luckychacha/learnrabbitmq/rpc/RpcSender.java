package com.luckychacha.learnrabbitmq.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leixinxin
 */
@Slf4j
@Component
public class RpcSender {

    @Autowired
    private RabbitTemplate rabbitTemplateNew;

    @Autowired
    private DirectExchange exchange;

    int start = 5;

    public void send() {
        System.out.println(" [x] Requesting fib(" + start + ")");
        System.out.println(exchange.getName());
        Integer response = (Integer) rabbitTemplateNew.convertSendAndReceive
                (exchange.getName(), "rpc", start);
        System.out.println(" [.] Got '" + response + "'");
    }
}