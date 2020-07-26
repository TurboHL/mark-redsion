package com.zhihuo.mark.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RedissionKeyManageService {

    @Autowired
    private RedissonClient redissonClient;

    public Long getKeysCount(){
        RKeys keys = redissonClient.getKeys();
        return keys.count();
    }

    public Long getExistKeysCount(String ...str){
        RKeys keys = redissonClient.getKeys();
        return keys.countExists(str);
    }

    public List<String> getAllKey(){
        RKeys keys = redissonClient.getKeys();
        Iterable<String> allKeys = keys.getKeys();
        List<String> list = new ArrayList<>();
        allKeys.forEach(key -> list.add(key));
        return list;
    }

    /**
     * Iterable<String> foundedKeys = keys.getKeysByPattern("test:str:00*");
     * @param patternKey
     * @return
     */
    public List<String> getKeysByPattern(String patternKey){
        RKeys keys = redissonClient.getKeys();
        Iterable<String> foundedKeys = keys.getKeysByPattern(patternKey);
        List<String> list = new ArrayList<>();
        foundedKeys.forEach(key -> list.add(key));
        return list;
    }

    /**
     *  long deletedKeysAmount = keys.deleteByPattern("test:str:00?");
     * @param patternKey
     * @return
     */
    public Long deletedKeysAmount(String patternKey){
        RKeys keys = redissonClient.getKeys();
        long deletedKeysAmount = keys.deleteByPattern(patternKey);
        return deletedKeysAmount;
    }

    public Long deletekey(String ...patternKey){
        RKeys keys = redissonClient.getKeys();
        long numOfDeletedKeys = keys.delete(patternKey);
        return numOfDeletedKeys;
    }

    public String getRandomKey(){
        RKeys keys = redissonClient.getKeys();
        String randomKey = keys.randomKey();
        return randomKey;
    }
}