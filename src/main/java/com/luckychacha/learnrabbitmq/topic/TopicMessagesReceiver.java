package com.luckychacha.learnrabbitmq.topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messages")
public class TopicMessagesReceiver implements ChannelAwareMessageListener {

    @Autowired
    private MessageConverter messageConverter;

//    @RabbitHandler
//    public void process(String msg) {
//        System.out.println("topic messages receiver:" + msg);
//    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();

        // 代表投递的标识符，唯一标识了当前信道上的投递，通过 deliveryTag ，消费者就可以告诉 RabbitMQ 确认收到了当前消息，见下面的方法
        long deliveryTag = messageProperties.getDeliveryTag();

        // 如果是重复投递的消息，redelivered 为 true
        Boolean redelivered = messageProperties.getRedelivered();

        // 获取生产者发送的原始消息
        Object originalMessage = messageConverter.fromMessage(message);

        System.out.println("consume message = " + originalMessage + " , deliveryTag = " + deliveryTag + " , redelivered = " + redelivered);

        // 代表消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
        channel.basicAck(deliveryTag, false);

        // 代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
//        channel.basicNack(deliveryTag, false, false);

        // 代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
//        channel.basicReject(deliveryTag,false);
    }
}
