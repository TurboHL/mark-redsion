package com.zhihuo.mark.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class RedissonBeanFactory {

    private static final ConcurrentHashMap<Integer,RedissonClient> CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    @Value("classpath:/redisson.yaml")
    private Resource configFile;

    public synchronized RedissonClient buildRedissonClient(Integer databse) {
        try{
            if (Objects.isNull(CONCURRENT_HASH_MAP.get(databse))) {
                Config config = Config.fromYAML(configFile.getInputStream());
                config.useSingleServer().setDatabase(databse);
                RedissonClient redissonClient = Redisson.create(config);
                CONCURRENT_HASH_MAP.put(databse, redissonClient);
                log.info("[构建-RedissonClient] database={},redisclient={}",databse,redissonClient);
            }
            return CONCURRENT_HASH_MAP.get(databse);
        }catch (Exception e){
            log.info("",e);
        }
        return null;
    }

//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException {
//        Config config = Config.fromYAML(configFile.getInputStream());
//        RedissonClient redissonClient = Redisson.create(config);
//        CONCURRENT_HASH_MAP.put(config.useSingleServer().getDatabase(),redissonClient);
//        return redissonClient;
//    }
}
