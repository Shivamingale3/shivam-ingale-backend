package com.shivamingale.backend.controller;

import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.ContactMe;
import com.shivamingale.backend.service.ContactMeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mails")
public class ContactMeController {
    private static final Logger logger = LoggerFactory.getLogger(ContactMeController.class);

    @Autowired
    private ContactMeService contactMeService;

    @GetMapping("/get-all")
    public ResponseEntity<SystemResponse> getAllMails() {
        List<ContactMe> contactMeInfo = contactMeService.getAllMails();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched Successfully!", contactMeInfo));
    }

}
