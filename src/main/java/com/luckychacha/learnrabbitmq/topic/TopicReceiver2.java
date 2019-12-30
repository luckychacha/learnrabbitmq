package com.luckychacha.learnrabbitmq.topic;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "topicQueue2")
public class TopicReceiver2 {

    @RabbitHandler
    public void process(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        //手动应答
        // 代表消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
        channel.basicAck(tag, false);
        // 代表消费者拒绝一条或多条消息，第二个参数标识一次是否拒绝多条消息，第三个参数标识是否把当前消息重新入队
//        channel.basicNack(tag, false, false);
        // 代表消费者拒绝当前消息，第二个参数标识重新入队
//        channel.basicReject(tag, true);
        log.info("receiver2: [{}]", msg);
    }
}
