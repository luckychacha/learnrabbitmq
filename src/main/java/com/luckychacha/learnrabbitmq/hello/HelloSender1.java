package com.luckychacha.learnrabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author luckychacha
 */
@Component
public class HelloSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello1, " + new Date();
        System.out.println(sendMsg);
        this.rabbitTemplate.convertAndSend("hello", sendMsg);
    }

    public void send(String msg) {
        System.out.println("sender1:" + msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
    }
}
