package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.Skills;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillsRepository extends MongoRepository<Skills, String> {
}
