package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByMobile(String username);
    Optional<User> findByEmail(String username);
}
