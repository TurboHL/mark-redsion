package com.zhihuo.mark.redis.service;

import org.redisson.api.RDelayedQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DelayedQueueService {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 参考地址 - https://blog.csdn.net/zsj777/article/details/82468212?utm_source=blogxgwz5
     * @param key
     * @param msg
     * @param delayTime
     * @param timeUnit
     */

    public void sendDelayMsg(String key, String msg, int delayTime,TimeUnit timeUnit) {
        RQueue<String> distinationQueue = redissonClient.getQueue(key);
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(distinationQueue);
        // 10秒钟以后将消息发送到指定队列
        delayedQueue.offer(msg, delayTime, timeUnit);
    }


}
