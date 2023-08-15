package com.tyl.redis.config;

import jodd.util.CharSequenceUtil;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tyl
 * @date 2023/8/10
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonAutoConfigurationCustomizer redissonAutoConfigurationCustomizer() {
        return config -> {
            config.useSingleServer().setPassword("Xyz123456");
        };
    }
}
