package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.Home;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HomeRepository extends MongoRepository<Home, String> {
    Optional<Home> findByDomain(String domain);

}
