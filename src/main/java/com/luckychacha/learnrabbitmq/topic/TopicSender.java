package com.luckychacha.learnrabbitmq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leixinxin
 */
@Slf4j
@Component
public class TopicSender {

    private AmqpTemplate rabbitTemplate;

    @Autowired
    public TopicSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void send() {
        String msg1 = "routingKey: a.orange.rabbit";
        String msg2 = "routingKey: lazy";
        String msg3 = "routingKey: lazy.orange.rabbit";
        log.info("sender: [{}]", msg1);
        log.info("sender: [{}]", msg2);
        log.info("sender: [{}]", msg3);

        this.rabbitTemplate.convertAndSend("topicExchange", "a.orange.rabbit", msg1);

        this.rabbitTemplate.convertAndSend("topicExchange", "lazy", msg2);

        this.rabbitTemplate.convertAndSend("topicExchange", "lazy.orange.rabbit", msg3);

    }

}
