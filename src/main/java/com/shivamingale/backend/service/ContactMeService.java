package com.shivamingale.backend.service;

import com.shivamingale.backend.model.ContactMe;
import com.shivamingale.backend.repository.ContactMeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactMeService {
    @Autowired
    private ContactMeRepository contactMeRepository;

    public List<ContactMe> getAllMails() {
        return contactMeRepository.findAll();
    }
}
