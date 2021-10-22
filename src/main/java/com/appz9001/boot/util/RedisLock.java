package com.appz9001.boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLock {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public boolean lock(String key){
        Boolean lock = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(key.getBytes(),"1".getBytes());
            }
        });
        return lock != null && lock;
    }

    public void unLock(String key){
        redisTemplate.delete(key);
    }
}
