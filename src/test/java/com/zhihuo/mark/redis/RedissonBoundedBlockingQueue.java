package com.zhihuo.mark.redis;

import org.junit.Test;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedissonBoundedBlockingQueue extends MarkRedisApplicationTests{

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test1(){
        try {
            RBoundedBlockingQueue<String> queue = redissonClient.getBoundedBlockingQueue("anyQueue");
            // 如果初始容量（边界）设定成功则返回`真（true）`，
            // 如果初始容量（边界）已近存在则返回`假（false）`。
            queue.trySetCapacity(2);
            queue.offer("1");
            queue.offer("2");
            queue.offer("3", 3, TimeUnit.SECONDS);

            //此时容量已满，下面代码将会被阻塞，直到有空闲为止。
            queue.put("4");
            queue.take();


            String obj = queue.peek();
            System.out.println(obj);
            String someObj = queue.poll();
            System.out.println(someObj);
            String ob = queue.poll(10, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
