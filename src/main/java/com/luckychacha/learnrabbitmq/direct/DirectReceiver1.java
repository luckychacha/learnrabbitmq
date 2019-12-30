package com.luckychacha.learnrabbitmq.direct;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author leixinxin
 */
@Slf4j
@Component
@RabbitListener(queues = "directQueue1")
public class DirectReceiver1 {

    @RabbitHandler
    public void process(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        log.info("receiver1: [{}]", msg);
    }
}
