package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.Education;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EducationRepository extends MongoRepository<Education, String> {
}
