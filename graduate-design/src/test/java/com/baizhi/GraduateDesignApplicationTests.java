package com.baizhi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduateDesignApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("test", "112233");
        String aThis = redisTemplate.opsForValue().get("test");
        System.out.println(aThis);
    }

}

