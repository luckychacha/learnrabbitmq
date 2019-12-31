package com.luckychacha.learnrabbitmq.controller;

import com.luckychacha.learnrabbitmq.callback.CallBackSender;
import com.luckychacha.learnrabbitmq.direct.DirectSender;
import com.luckychacha.learnrabbitmq.dlx.DlxSender;
import com.luckychacha.learnrabbitmq.fanout.FanoutSender;
import com.luckychacha.learnrabbitmq.headers.HeadersSender;
import com.luckychacha.learnrabbitmq.hello.HelloReceiver1;
import com.luckychacha.learnrabbitmq.hello.HelloSender1;
import com.luckychacha.learnrabbitmq.hello.HelloSender2;
import com.luckychacha.learnrabbitmq.rpc.RpcSender;
import com.luckychacha.learnrabbitmq.topic.TopicSender;
import com.luckychacha.learnrabbitmq.user.UserSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    private HelloSender1 helloSender1;

    private HelloSender2 helloSender2;

    private UserSender1 userSender1;

    private TopicSender topicSender;

    private FanoutSender fanoutSender;


    private CallBackSender callBackSender;

    private DirectSender directSender;

    private HeadersSender headersSender;

    private RpcSender rpcSender;

    private DlxSender dlxSender;

    @Autowired
    public RabbitTest(HelloSender1 helloSender1,
                      HelloSender2 helloSender2,
                      UserSender1 userSender1,
                      TopicSender topicSender,
                      FanoutSender fanoutSender,
                      CallBackSender callBackSender,
                      DirectSender directSender,
                      HeadersSender headersSender,
                      RpcSender rpcSender,
                      DlxSender dlxSender) {
        this.helloSender1 = helloSender1;
        this.helloSender2 = helloSender2;
        this.userSender1 = userSender1;
        this.topicSender = topicSender;
        this.fanoutSender = fanoutSender;
        this.callBackSender = callBackSender;
        this.directSender = directSender;
        this.headersSender = headersSender;
        this.rpcSender = rpcSender;
        this.dlxSender = dlxSender;
    }
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

    @PostMapping("sendDirect")
    public void sendDirect() {
        directSender.send();
    }

    @PostMapping("fanout")
    public void sendFanout() {
        fanoutSender.send();
    }


    @PostMapping("sendHeaders")
    public void sendHeaders() {
        headersSender.send();
    }

    @PostMapping("sendRpc")
    public void sendRpc() {
        rpcSender.send();
    }


    @PostMapping("sendDlx")
    public void sendDlx() {
        dlxSender.send("test", 5, "queue1");
    }

    @PostMapping("callback")
    public void callback() throws Exception  {
        callBackSender.send();
    }
}
