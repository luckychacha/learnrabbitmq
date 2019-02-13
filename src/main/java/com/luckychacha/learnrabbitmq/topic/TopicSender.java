package com.luckychacha.learnrabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg1 = "i am topic.message msg===";
        System.out.println("sender1: " + msg1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        String msg2 = "i am topic.messages msg-----";
        System.out.println("sender2:" + msg2);
        //exchange, routingKey
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);

    }

}
