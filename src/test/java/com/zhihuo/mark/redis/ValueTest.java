package com.zhihuo.mark.redis;

import com.zhihuo.mark.redis.service.LockService;
import org.junit.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class ValueTest extends MarkRedisApplicationTests {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private LockService lockService;

    @Test
    public void test11111333() {
        RLock lock = redissonClient.getLock("my-long");
        lock.lock();
        System.out.println("执行任务");
        lock.unlock();
    }

    @Test
    public void test2() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                lockService.getFairLock();
            }).start();
        }

        Thread.sleep(4000000);
    }

    @Test
    public void opSetObject() {
        for(int i=0;i<10;i++){
            RBucket<String> bucket = redissonClient.getBucket("test:str:00" + i);
            bucket.set("test",5, TimeUnit.MINUTES);
        }
    }

    @Test
    public void opGetObject() {
        for(int i=0;i<10;i++){
            RBucket<String> bucket = redissonClient.getBucket("test:str:00"+ i);
            System.out.println(bucket.get());
        }
    }

}
