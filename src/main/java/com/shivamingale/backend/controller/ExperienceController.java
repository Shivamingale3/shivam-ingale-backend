package com.shivamingale.backend.controller;


import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.Experience;
import com.shivamingale.backend.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/get-all")
    public ResponseEntity<SystemResponse> getAllExperience() {
        List<Experience> experienceInfo = experienceService.getAllExperience();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched Successfully", experienceInfo));
    }

}
