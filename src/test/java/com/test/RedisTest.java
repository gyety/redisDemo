package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    RedissonClient client;
    @Test
    public void testAdd() {
        RBucket bucket = client.getBucket("test");
        bucket.set("test4");
    }
    @Test
    public void testLock(){
        RLock lock=client.getLock("mylock");

        Thread t1=new Thread(()->{
            lock.lock(1,TimeUnit.SECONDS);
            System.out.println("i get lock");
        });
        t1.start();
        Thread t2=new Thread(()->{
           lock.lock(1,TimeUnit.SECONDS);
            System.out.println("t2 get lock");

        });
        t2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testBloom(){
        RBloomFilter<Object> bloomFilter=client.getBloomFilter("mybloom");
        System.out.println(bloomFilter.isExists());
        RBloomFilter<Object> bloomFilter2=client.getBloomFilter("mybloom2");
        System.out.println(bloomFilter2.isExists());
        bloomFilter.tryInit(1000,0.03);
        bloomFilter.add("user1");
        System.out.println(bloomFilter.isExists());

      /*  for (int i=0;i<1000;i++){
            bloomFilter.add("瓜田李下 "+i);
        }
        System.out.println("'瓜田李下 1'是否存在："+bloomFilter.contains("瓜田李下 "+1));
        System.out.println("'海贼王'是否存在："+bloomFilter.contains("海贼王"));
        System.out.println("预计插入数量："+bloomFilter.getExpectedInsertions());
        System.out.println("容错率："+bloomFilter.getFalseProbability());
        System.out.println("hash函数的个数："+bloomFilter.getHashIterations());
        System.out.println("插入对象的个数："+bloomFilter.count());*/

    }

    @Test
    public void testList(){
        RList list=client.getList("testlist");
        list.add("t1");
        list.add("t2");
        list.add("t3");
        System.out.println(list.range(0,2));

    }
}
