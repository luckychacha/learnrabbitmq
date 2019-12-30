package com.luckychacha.learnrabbitmq.user;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        User user = new User();
        user.setName("userName");
        user.setPass("pass12345");
        System.out.println("sender : name:" + user.getName() + " pass:" + user.getPass());
        this.rabbitTemplate.convertAndSend("user", user);
    }
}
