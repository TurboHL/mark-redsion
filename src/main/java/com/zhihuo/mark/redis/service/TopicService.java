package com.zhihuo.mark.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RPatternTopic;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.client.protocol.pubsub.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TopicService {
    @Autowired
    private RedissonClient redissonClient;

    public<T> Long publishTopic(String topicName, T message) {
        RTopic topic = redissonClient.getTopic(topicName);
        long clientsReceivedMessage = topic.publish(message);
        log.info("clientsReceivedMessage={}",clientsReceivedMessage);
        return clientsReceivedMessage;
    }

    public<T> Boolean creatTopicListen(String topicName,Class<T> tclass) {
        RTopic topic = redissonClient.getTopic(topicName);
        topic.addListener(tclass,(CharSequence charSequence,T message) -> {
            log.info("charSequence={},message={}",charSequence,message);
        });
        return true;
    }

    public<T> Boolean createPatternTopic(String patternTopic) {
        // 订阅所有满足`topic1.*`表达式的话题
        RPatternTopic topic1 = redissonClient.getPatternTopic("topic1.*");
        int listenerId = topic1.addListener(Message.class, new PatternMessageListener<Message>() {
            @Override
            public void onMessage(CharSequence pattern, CharSequence channel, Message message) {

            }
        });
        return true;
    }

}
