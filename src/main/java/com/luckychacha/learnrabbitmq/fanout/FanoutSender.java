package com.luckychacha.learnrabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "fanoutSender: hello i am fanoutsender!";
        System.out.println(message);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", message);
    }

}
