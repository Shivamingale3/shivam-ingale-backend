package com.shivamingale.backend.controller;


import com.shivamingale.backend.service.AboutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/about")
public class AboutController {
    private static final Logger logger = LoggerFactory.getLogger(AboutController.class);

    @Autowired
    private AboutService aboutService;

}
