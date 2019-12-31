package com.luckychacha.learnrabbitmq.dlx;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 转发队列
 * @author leixinxin
 */
@Slf4j
@Component
@RabbitListener(queues = "transQueue")
public class TransProcess {

    private AmqpTemplate rabbitTemplate;

    @Autowired
    public TransProcess(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitHandler
    public void process(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        String content = msg.split(":")[0];
        String delayQueueName = msg.split(":")[1];
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("进行转发: transQueue,[{}-{}]", now, msg);
        rabbitTemplate.convertAndSend("delayProcessExchange", delayQueueName, content);
    }
}
