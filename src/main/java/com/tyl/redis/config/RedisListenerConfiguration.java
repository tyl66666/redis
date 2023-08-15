package com.tyl.redis.config;

import com.tyl.redis.listener.RedisMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

/**
 * @author tyl
 */
@Configuration
public class RedisListenerConfiguration {
    /** 这个是通知的规则 */
    public String pattern="__keyevent@0__:expired";

    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory redisConnection) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnection);

        /**
         * Topic是消息发布(Pub)者和订阅(Sub)者之间的传输中介
         */
        Topic topic = new PatternTopic(this.pattern);

        container.addMessageListener(new RedisMessageListener(), topic);
        return container;
    }
}
