package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.ContactMe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContactMeRepository extends MongoRepository<ContactMe, String> {
    Optional<ContactMe> findByDomain(String domain);

}
