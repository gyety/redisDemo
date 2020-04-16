package com.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class SingleRedis {
    @Bean
    public RedissonClient getSingle() throws IOException {
        Config config=Config.fromYAML(new File("E:\\IDEA\\redisDemo\\redisDemo\\src\\main\\resources\\singleServerConfig.yaml"));
        return Redisson.create(config);

    }
}
