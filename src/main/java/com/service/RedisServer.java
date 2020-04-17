package com.service;

import com.exception.RedissClientException;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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



}
