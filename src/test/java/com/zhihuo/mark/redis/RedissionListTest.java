package com.zhihuo.mark.redis;

import com.zhihuo.mark.redis.service.RedissionListService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RedissionListTest extends MarkRedisApplicationTests{

    @Autowired
    private RedissionListService redissionListService;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test1() {
        RList<String> list = redissonClient.getList("anyList");

        String value = "3333";
        list.add(value);
        String aa = list.get(0);
        log.info("aa={}",aa);

        list.remove(value);

        aa = list.get(0);
        log.info("aa={}",aa);
    }


}
