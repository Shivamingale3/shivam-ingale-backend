package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.About;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AboutRepository extends MongoRepository<About, String> {

}
