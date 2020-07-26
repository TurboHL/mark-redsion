package com.zhihuo.mark.redis.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LockService {

    @Autowired
    private RedissonClient redissonClient;

    public void lock() {
        long time = System.currentTimeMillis();
        RLock lock = redissonClient.getLock("my-long");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "++++++++++" + ((System.currentTimeMillis() - time) / 1000));
        lock.unlock();
    }

    public void getFairLock() {
        long time = System.currentTimeMillis();
        RLock fairLock = redissonClient.getFairLock("anyLock");
        fairLock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "++++++++++" + ((System.currentTimeMillis() - time) / 1000));
        fairLock.unlock();
    }
}
