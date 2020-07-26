package com.zhihuo.mark.redis;

import com.zhihuo.mark.redis.model.Person;
import com.zhihuo.mark.redis.service.TopicService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TopicTest extends MarkRedisApplicationTests{

    @Autowired
    private TopicService publishTopicService;

    @Test
    public void sendMsg(){
        for (int i = 0; i < 10; i++) {
            publishTopicService.publishTopic("myTopic", "hello word -" + i + "-" + System.currentTimeMillis());
        }
    }
    @Test
    public void receiveMsg() throws InterruptedException {
        publishTopicService.creatTopicListen("myTopic",String.class);
        Thread.sleep(100000000000000000L);
    }


    @Test
    public void sendMsgObject(){
        for (int i = 0; i < 10; i++) {
            Person p = Person.builder().name("test-" + i).age(i).idNo(System.currentTimeMillis() + "").build();
            publishTopicService.publishTopic("myTopic",p);
        }
    }
    @Test
    public void receiveMsgObject() throws InterruptedException {
        publishTopicService.creatTopicListen("myTopic", Person.class);
        Thread.sleep(100000000000000000L);
    }

    @Test
    public void sendMsgList(){
        for (int i = 0; i < 10; i++) {
            Person p = Person.builder().name("test-" + i).age(i).idNo(System.currentTimeMillis() + "").build();
            publishTopicService.publishTopic("myTopic", Lists.newArrayList(p));
        }
    }
    @Test
    public void receiveMsgList() throws InterruptedException {
        publishTopicService.creatTopicListen("myTopic", List.class);
        Thread.sleep(100000000000000000L);
    }
}
