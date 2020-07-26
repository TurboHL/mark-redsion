package com.zhihuo.mark.redis.service;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedissionListService {

    @Autowired
    private RedissonClient redissonClient;

    public <T> RList<T> buildList(String key,T t) {
        RList<T> list = redissonClient.getList(key);
        return list;
    }

    public <T> void add(String key,T t){
        this.buildList(key,t).add(t);
    }

    public <T> T get(String key,T t,int index){
        return this.buildList(key,t).get(index);
    }
}
