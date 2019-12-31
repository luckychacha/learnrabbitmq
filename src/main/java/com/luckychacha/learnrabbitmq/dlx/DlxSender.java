package com.luckychacha.learnrabbitmq.dlx;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * 生产者
 * @author Leixinxin
 * @date 2019/12/30 1:28 PM
 */
@Slf4j
@Component
public class DlxSender {

    private AmqpTemplate rabbitTemplate;

    @Autowired
    public DlxSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void send(String msg, long time, String delayQueueName) {
        //rabbit默认为毫秒级
        long times = time * 1000;
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(times));
                //message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            }
        };
        String info = msg + ":" + delayQueueName;
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("生产者：写入DLX队列，[{}-{}]", now, info);
        this.rabbitTemplate.convertAndSend(
                "delayProcessExchange",
                "dlx",
                info,
                processor
        );
    }
}
