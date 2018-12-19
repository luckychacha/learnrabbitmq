package com.luckychacha.learnrabbitmq.user;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "user")
public class UserReceiver1 {

    @RabbitHandler
    public void process(User user) {
        System.out.println("receiver:user info : name:" + user.getName() + " pass:" + user.getPass());
    }
}
