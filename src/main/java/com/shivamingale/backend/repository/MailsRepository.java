package com.shivamingale.backend.repository;

import com.shivamingale.backend.model.Mails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailsRepository extends MongoRepository<Mails, String> {
}
