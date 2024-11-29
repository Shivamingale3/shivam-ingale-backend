package com.shivamingale.backend.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RedisKeys {
    @Value("${redis.keys.home.root}")
    private String redisKeyHome;

    @Value("${redis.keys.home.about}")
    private String redisKeyAbout;

    @Value("${redis.keys.home.education}")
    private String redisKeyEducation;

    @Value("${redis.keys.home.experience}")
    private String redisKeyExperience;

    @Value("${redis.keys.home.projects}")
    private String redisKeyProjects;

    @Value("${redis.keys.home.skills}")
    private String redisKeySkills;

    @Value("${redis.keys.home.contacted}")
    private String redisKeyContacted;


}
