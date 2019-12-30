package com.luckychacha.learnrabbitmq.headers;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import sun.jvm.hotspot.runtime.Bytes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author leixinxin
 */
@Slf4j
@Component
public class HeadersReceiver1 {

    @RabbitHandler
    @RabbitListener(queues = "headersQueue1")
    public void process(byte[] bytes, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        String a = new String(bytes, StandardCharsets.UTF_8);
        log.info("receiver1: [{}]", a);
    }
}
