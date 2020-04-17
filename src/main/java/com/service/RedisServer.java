package com.service;

import com.exception.RedissClientException;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisServer {
    @Autowired
    RedissonClient client;
    /**
     * Redisson 创建bloomFilter
     * */
    public <T> RBloomFilter<T> createBloom(String bname,Long size,double accuracy){
        RBloomFilter<T> bloomFilter=client.getBloomFilter(bname);
        if(bloomFilter.isExists()){
            throw new RedissClientException("bloom is exists");
        }
        bloomFilter.tryInit(size,accuracy);
        return bloomFilter;
    }
    /**
     * 获取热点数据
     * */
    public  <V> V getDate(String key){
        RBucket<V> str=client.getBucket(key);
        V  data=(V)str.get();
        //缓存未命中
        if(data==null){
            RLock lock=client.getLock("mylock");
            try {
                //获取锁
                if(lock.tryLock(500,TimeUnit.MICROSECONDS)){
                    data=getDataFromDB(key);
                    if(data!=null){
                        str.set(data);
                    }
                }else{
                    //防止重复查库，线程等待后再从缓存中获取
                    Thread.sleep(500);
                    data=(V)str.get();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
        return data;
    }

    public <V> V getDataFromDB(String key){
        //V  v=sql//
        return null;
    }


}
