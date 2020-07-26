package com.zhihuo.mark.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = MarkRedisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
class MarkRedisApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void contextLoads() {
    }

}
