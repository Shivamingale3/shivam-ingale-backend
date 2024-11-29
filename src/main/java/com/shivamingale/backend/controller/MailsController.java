package com.shivamingale.backend.controller;

import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.Mails;
import com.shivamingale.backend.service.MailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mails")
public class MailsController {
    @Autowired
    private MailsService mailsService;

    @GetMapping("/get-all")
    public ResponseEntity<SystemResponse> getAllMails() {
        List<Mails> mailsInfo = mailsService.getAllMails();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched Successfully!", mailsInfo));
    }

}
