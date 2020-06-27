package com.cn.cloud.ofo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ServiceApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        //String OnlyId= UUID.randomUUID().toString().replace("-","");
        //System.out.println(OnlyId);
        redisTemplate.opsForValue().set("a",1,1, TimeUnit.MINUTES);
        //System.out.println(redisTemplate.opsForValue().get("047d13a4407942d69b1db4326ac8e8c9"));
    }

    @Test
    void deltest(){
        redisTemplate.delete("a");
    }
}
