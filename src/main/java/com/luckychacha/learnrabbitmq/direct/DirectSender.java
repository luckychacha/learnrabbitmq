package com.luckychacha.learnrabbitmq.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leixinxin
 * @date 2019/12/30 1:28 PM
 */
@Slf4j
@Component
public class DirectSender {

    private AmqpTemplate rabbitTemplate;

    @Autowired
    public DirectSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void send() {
        String msg1 = "routingKey: error";
        String msg2 = "routingKey: info";
        String msg3 = "routingKey: warning";
        log.info("sender: [{}]", msg1);
        log.info("sender: [{}]", msg2);
        log.info("sender: [{}]", msg3);

        this.rabbitTemplate.convertAndSend("directExchange", "error", msg1);

        this.rabbitTemplate.convertAndSend("directExchange", "error1", msg1);

        this.rabbitTemplate.convertAndSend("directExchange", "info", msg2);

        this.rabbitTemplate.convertAndSend("directExchange", "warning", msg3);

    }
}
