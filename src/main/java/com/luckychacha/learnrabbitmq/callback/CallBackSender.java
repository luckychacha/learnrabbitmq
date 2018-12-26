package com.luckychacha.learnrabbitmq.callback;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplatenew;

    public void send(Channel channel) throws Exception {
        channel.txSelect();
        rabbitTemplatenew.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        String msg = "callback sender: i am provider!!! id:" + correlationData.getId();
        System.out.println(msg);
        System.out.println("callback sender task uuid is :" + correlationData.getId());
        this.rabbitTemplatenew.convertAndSend("exchange", "topic.messages", msg, correlationData );
        channel.txCommit();
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("callback confirm success:" + correlationData.getId());
        } else {
            System.out.println("callback confirm fail:" + correlationData.getId());
        }
    }
}
