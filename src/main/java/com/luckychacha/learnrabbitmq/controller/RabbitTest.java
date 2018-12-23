package com.luckychacha.learnrabbitmq.controller;

import com.luckychacha.learnrabbitmq.callback.CallBackSender;
import com.luckychacha.learnrabbitmq.fanout.FanoutSender;
import com.luckychacha.learnrabbitmq.hello.HelloReceiver1;
import com.luckychacha.learnrabbitmq.hello.HelloSender1;
import com.luckychacha.learnrabbitmq.hello.HelloSender2;
import com.luckychacha.learnrabbitmq.topic.TopicSender;
import com.luckychacha.learnrabbitmq.user.UserSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private HelloSender1 helloSender1;

    @Autowired
    private HelloSender2 helloSender2;

    @Autowired
    private UserSender1 userSender1;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private CallBackSender callBackSender;
    /**
     * oneToOne
     */
    @PostMapping("hello")
    public void hello() {
        helloSender1.send();
    }

    /**
     * oneToMany
     */
    @PostMapping("helloToMany")
    public void oneToMany() {
        for (int i = 0; i < 100; i++) {
            helloSender1.send("helloMsg: hello" + i);
        }
    }

    /**
     * manyToMany
     */
    @PostMapping("helloManyToMany")
    public void manyToMany() {
        for (int i = 0; i < 100; i++) {
            helloSender1.send("No." + i + ",hellooooo!!!----from sender1");
            helloSender2.send("No." + i + ",hellooooo!!!----from sender2");
        }
    }

    /**
     * sendEntity
     */
    @PostMapping("sendEntity")
    public void sendEntity() {
        userSender1.send();
    }

    @PostMapping("sendTopic")
    public void sendTopic() {
        topicSender.send();
    }

    @PostMapping("fanout")
    public void sendFanout() {
        fanoutSender.send();
    }

    @PostMapping("callback")
    public void callback() {
        callBackSender.send();
    }
}
