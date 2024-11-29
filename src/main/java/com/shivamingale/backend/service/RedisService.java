package com.shivamingale.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Save data to Redis with an optional expiration time.
     *
     * @param key   the key to store the data
     * @param value the value to store
     * @param ttl   time to live in seconds (pass 0 for no expiration)
     */
    public void setData(String key, Object value, long ttl) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value must not be null");
        }

        try {
            redisTemplate.opsForValue().set(key, value);

            // If ttl > 0, set the expiration time in Redis
            if (ttl > 0) {
                redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
            } else {
                // Optionally, set an expiration of -1 to indicate no expiration
                redisTemplate.expire(key, -1, TimeUnit.SECONDS);
            }

            logger.info("Data successfully saved to Redis with key: {}", key);
        } catch (Exception e) {
            logger.error("Error while saving data to Redis: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving data to Redis", e);
        }
    }

    /**
     * Retrieve data from Redis.
     *
     * @param key the key to look for
     * @return the value stored in Redis, or null if not found
     */
    public Object getData(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            Object value = redisTemplate.opsForValue().get(key);

            if (value == null) {
                logger.warn("Data not found in Redis for key: {}", key);
            }

            return value;
        } catch (Exception e) {
            logger.error("Error while retrieving data from Redis: {}", e.getMessage(), e);
            throw new RuntimeException("Error while retrieving data from Redis", e);
        }
    }

    /**
     * Delete data from Redis by key.
     *
     * @param key the key to delete
     */
    public void deleteData(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            redisTemplate.delete(key);
            logger.info("Data successfully deleted from Redis for key: {}", key);
        } catch (Exception e) {
            logger.error("Error while deleting data from Redis: {}", e.getMessage(), e);
            throw new RuntimeException("Error while deleting data from Redis", e);
        }
    }

    /**
     * Check if a key exists in Redis.
     *
     * @param key the key to check
     * @return true if the key exists, false otherwise
     */
    public boolean hasKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            boolean exists = redisTemplate.hasKey(key);
            logger.info("Key {} exists: {}", key, exists);
            return exists;
        } catch (Exception e) {
            logger.error("Error while checking key existence in Redis: {}", e.getMessage(), e);
            throw new RuntimeException("Error while checking key existence in Redis", e);
        }
    }
}
