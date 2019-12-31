package com.luckychacha.learnrabbitmq.dlx;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 消费者
 * @author leixinxin
 */
@Slf4j
@Component
@RabbitListener(queues = "dlxQueue1")
public class DlxReceiver {

    @RabbitHandler
    public void process(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("延迟队列: queue1开始执行，[{}-{}]",
                now,
                msg
        );
    }
}
