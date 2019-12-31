package com.luckychacha.learnrabbitmq.headers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author Leixinxin
 * @date 2019/12/30 1:28 PM
 */
@Slf4j
@Component
public class HeadersSender {

    private AmqpTemplate rabbitTemplate;

    @Autowired
    public HeadersSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void send() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("queueName", "headersQueue1");
        messageProperties.setHeader("bindType", "whereAll");
        log.info("sender: [{}]", messageProperties);
        Message message = new Message("headersQueue1+whereAll".getBytes(), messageProperties);

        this.rabbitTemplate.convertAndSend("headersExchange", "", message);

        MessageProperties messageProperties2 = new MessageProperties();
        messageProperties2.setHeader("queueName", "headersQueue2");
        messageProperties2.setHeader("bindType", "whereAny");
        log.info("sender: [{}]", messageProperties);
        Message message2 = new Message("headersQueue2+whereAny".getBytes(StandardCharsets.UTF_8), messageProperties2);
        this.rabbitTemplate.convertAndSend("headersExchange", "", message2);

        MessageProperties messageProperties3 = new MessageProperties();
        messageProperties3.setHeader("queueName", "headersQueue1");
        messageProperties3.setHeader("bindType", "whereAny");
        log.info("sender: [{}]", messageProperties);
        Message message3 = new Message("headersQueue3+whereAny".getBytes(StandardCharsets.UTF_8), messageProperties3);

        this.rabbitTemplate.convertAndSend("headersExchange", "", message3);

        MessageProperties messageProperties4 = new MessageProperties();
        messageProperties4.setHeader("queueName", "???");
        messageProperties4.setHeader("bindType", "aaa");
        messageProperties4.setHeader("test", "aaa");
        log.info("sender: [{}]", messageProperties);
        Message message4 = new Message("headersQueue4+whereAny".getBytes(StandardCharsets.UTF_8), messageProperties4);

        this.rabbitTemplate.convertAndSend("headersExchange", "", message4);
    }
}
