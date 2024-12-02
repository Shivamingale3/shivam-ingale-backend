package com.shivamingale.backend.controller;


import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.Education;
import com.shivamingale.backend.service.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {
    private static final Logger logger = LoggerFactory.getLogger(EducationController.class);

    @Autowired
    private EducationService educationService;

    @GetMapping("/get-all")
    public ResponseEntity<SystemResponse> getAllEducation() {
        List<Education> educationInfo = educationService.getAllEducations();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched Successfully!", educationInfo));
    }
}
