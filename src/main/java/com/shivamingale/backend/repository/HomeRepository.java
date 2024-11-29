package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.Home;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeRepository extends MongoRepository<Home, String> {
}
