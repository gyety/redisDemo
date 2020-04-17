package com.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@Configuration
public class SingleRedis {
    @Bean
    public RedissonClient getSingle() throws IOException {
        Config config=Config.fromYAML(ResourceUtils.getFile("classpath:singleServerConfig.yaml"));
        return Redisson.create(config);

    }
}
