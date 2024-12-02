package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.About;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AboutRepository extends MongoRepository<About, String> {
    Optional<About> findByDomain(String domain);
}
