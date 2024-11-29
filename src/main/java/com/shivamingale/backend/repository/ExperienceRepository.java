package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExperienceRepository extends MongoRepository<Experience, String> {
}
