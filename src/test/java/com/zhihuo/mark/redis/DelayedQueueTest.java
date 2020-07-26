package com.zhihuo.mark.redis;

import org.junit.Test;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class DelayedQueueTest extends MarkRedisApplicationTests{

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() throws InterruptedException {

        //参考地址 ：https://blog.csdn.net/zsj777/article/details/82468212?utm_source=blogxgwz5

        // distinationQueue 可以是任意的队列
//        RQueue<String> distinationQueue = redissonClient.getQueue("myqueue");

        // distinationQueue 可以是任意的队列
        RBoundedBlockingQueue<String> distinationQueue = redissonClient.getBoundedBlockingQueue("myqueue");

        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(distinationQueue);
        // 10秒钟以后将消息发送到指定队列
        delayedQueue.offer("msg1", 5, TimeUnit.SECONDS);
        // 一分钟以后将消息发送到指定队列
        delayedQueue.offer("msg2", 10, TimeUnit.SECONDS);

        while (true){
/*            String poll = delayedQueue.poll();
            if (null!=poll && !"".equals(poll)) {
                System.out.println("========================");
                System.out.println(poll);
                System.out.println("========================");
            }*/
            String poll = distinationQueue.poll();
            if (null!=poll && !"".equals(poll)) {
                System.out.println("========================");
                System.out.println(poll);
                System.out.println("========================");
            }
        }
    }

    @Test
    public void test2(){

    }
}
