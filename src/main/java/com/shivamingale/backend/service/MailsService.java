package com.shivamingale.backend.service;

import com.shivamingale.backend.model.Mails;
import com.shivamingale.backend.repository.MailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MailsService {
    @Autowired
    private MailsRepository mailsRepository;

    public List<Mails> getAllMails() {
        return mailsRepository.findAll();
    }
}
