package com.luckychacha.learnrabbitmq.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author leixinxin
 */
@Slf4j
@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplateNew;

    @Autowired
    public CallBackSender(RabbitTemplate rabbitTemplateNew) {
        this.rabbitTemplateNew = rabbitTemplateNew;
    }

    @Transactional(rollbackFor = Exception.class)
    public void send() {
        this.rabbitTemplateNew.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        String msg = "callback test";
        log.info("callback sender:producer id:[{}]", correlationData.getId());
        log.info("callback sender task uuid is :[{}]", correlationData.getId());
        this.rabbitTemplateNew.convertAndSend("topicExchange", "a.b.rabbit", msg, correlationData );
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("callback confirm success:[{}]",correlationData.getId());
        } else {
            log.info("callback confirm fail:[{}]", correlationData.getId());
        }
    }
}
