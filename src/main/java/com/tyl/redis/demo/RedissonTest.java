package com.tyl.redis.demo;


import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tyl
 */
@Component
public class RedissonTest {


    private  final RedissonClient redissonClient;


    private  RedisTemplate<String,Object> redisTemplate;

    @Autowired(required = false)
    public RedissonTest(RedissonClient redissonClient,RedisTemplate<String,Object> redisTemplate) {
        this.redissonClient = redissonClient;
        this.redisTemplate=redisTemplate;
    }

    public void test(){
        /**
         * 当用redis实现分布式锁的时候 最好使用redisson
         * */
        RLock rLock =redissonClient.getLock("aa");
        rLock.lock();
        //实现自动续期 设置30s 当任务还未完成 锁到时间也不会释放
        rLock.lock(30, TimeUnit.SECONDS);
        //手动释放锁
        rLock.unlock();

        redisTemplate.opsForValue().set("name","1",3,TimeUnit.SECONDS);

        /**处理批操作 redis中本来也有批量的操作 要是可以用原生的批处理操作就用原生的
         *
         *  List<Object> objects 里面封装的结果是 判断命令执行成功的Boolean值
         *
         * */

        List<Object> objects = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                //connection 下面都是原生命令
                return null;
            }
        });
    }

}
