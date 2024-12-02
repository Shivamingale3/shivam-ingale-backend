package com.shivamingale.backend.controller;


import com.shivamingale.backend.service.ExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    private static final Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    @Autowired
    private ExperienceService experienceService;

}
