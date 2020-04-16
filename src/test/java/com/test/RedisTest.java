package com.test;


import io.rebloom.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    RedissonClient client;
    @Test
    public void testAdd() {
        RLock lock = client.getLock("mylock");
        System.out.println(lock.getName());
    }
}
