package com.web.assignment.appointment.repository.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public RedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(String key, String user) {
        hashOperations.put("MERCY", key, user);
    }

    public String findById(String key) {
        return (String) hashOperations.get("MERCY", key);
    }

    public void delete(String key) {
        hashOperations.delete(key);
    }
}
