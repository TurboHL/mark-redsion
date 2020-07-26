package com.zhihuo.mark.redis;

import com.zhihuo.mark.redis.config.RedissonBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SwitchRedisDBRest extends MarkRedisApplicationTests {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedissonBeanFactory redissonBeanFactory;

    @Test
    public void testDb(){
        RBucket<String> bucket = redissonClient.getBucket("my:bucket:test001");
        bucket.set("test0001111");

        String data = bucket.get();
        log.info(" bucket.get()={}", data);

        RedissonClient redissonClientDB = redissonBeanFactory.buildRedissonClient(3);
        RBucket<String> bucket1 = redissonClientDB.getBucket("photo:correct:result:pushflag:2020022416080185541:oyu3N0X-jvsSabhqSPhCQd42yZ-A");
        log.info(" bucket1.get()={}", bucket1.get());

        redissonClientDB = redissonBeanFactory.buildRedissonClient(3);
        bucket1 = redissonClientDB.getBucket("photo:correct:result:pushflag:2020022416080185541:oyu3N0X-jvsSabhqSPhCQd42yZ-A");
        log.info(" bucket1-3.get()={}", bucket1.get());

        redissonClientDB = redissonBeanFactory.buildRedissonClient(7);
        bucket1 = redissonClientDB.getBucket("2020-04-04_1011013_uv_count");
        log.info(" bucket1-7.get()={}", bucket1.get());

        redissonClientDB = redissonBeanFactory.buildRedissonClient(7);
        bucket1 = redissonClientDB.getBucket("2020-04-04_1011013_uv_count");
        log.info(" bucket1-7.get()={}", bucket1.get());
    }

}
