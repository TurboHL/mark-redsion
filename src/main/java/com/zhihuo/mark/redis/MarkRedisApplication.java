package com.zhihuo.mark.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MarkRedisApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MarkRedisApplication.class, args);
    }

}
