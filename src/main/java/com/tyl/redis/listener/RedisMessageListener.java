package com.tyl.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author tyl
 */


public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();

        String expiredKey = new String(body);

        System.out.println("监听到已过期的key：" + expiredKey);

        /**
         * 监听到过期事件回调
         * TODO:
         */
    }
}
