package com.luckychacha.learnrabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        System.out.println("sender2:" + msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
    }
}
