package com.shivamingale.backend.util;

import com.mongodb.MongoClientException;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class StartupRunner implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;  // MongoDB Template
    @Autowired
    private StringRedisTemplate redisTemplate;  // Redis Template
    @Autowired
    private S3Client s3Client;  // Use S3Client from AWS SDK v2  // Amazon S3 Client

    // Instance logger (no static modifier)
    private final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Custom Startup Message
        logger.info("**********************************************************************************************************************");

        // Check connections and log appropriate messages
        if (checkMongoDBConnection()) {
            logger.info("******************************************        Connected to MongoDB.      *****************************************");
        }
        if (checkRedisConnection()) {
            logger.info("******************************************        Connected to Redis.         ****************************************");
        }
        if (checkS3Connection()) {
            logger.info("******************************************          Connected to S3           ****************************************");
        }

        logger.info("****************************************** Backend has started successfully. *****************************************");
        logger.info("**********************************************************************************************************************");
    }

    // Method to check MongoDB connection
    private boolean checkMongoDBConnection() {
        try {
            MongoDatabase database = mongoTemplate.getDb();
            if (database != null) {
                return true;
            }
        } catch (MongoClientException e) {
            logger.error("MongoDB connection failed: " + e.getMessage());
        }
        return false;
    }

    // Method to check Redis connection
    private boolean checkRedisConnection() {
        try {
            String pingResponse = redisTemplate.getConnectionFactory().getConnection().ping();
            if ("PONG".equalsIgnoreCase(pingResponse)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("Redis connection failed: " + e.getMessage());
        }
        return false;
    }

    // Method to check Amazon S3 connection
    private boolean checkS3Connection() {
        try {
            // Perform a simple action like listing buckets
            s3Client.listBuckets();
            return true;
        } catch (Exception e) {
            logger.error("Amazon S3 connection failed: " + e.getMessage());
        }
        return false;
    }
}
