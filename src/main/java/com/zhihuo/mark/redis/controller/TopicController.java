package com.zhihuo.mark.redis.controller;

import com.zhihuo.mark.redis.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisson")
public class TopicController {

    @Autowired
    private TopicService publishTopicService;

    @RequestMapping("/sendMsg")
    public void sendMsg(String topic,String message) {
        publishTopicService.publishTopic(topic,message);
    }

    @RequestMapping("/recMsg")
    public void recMsg(String topic) {
        publishTopicService.creatTopicListen(topic,String.class);
    }

}
