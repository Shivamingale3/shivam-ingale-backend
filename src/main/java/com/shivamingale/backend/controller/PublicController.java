package com.shivamingale.backend.controller;

import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.*;
import com.shivamingale.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AboutService aboutService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private SkillsService skillsService;
    @Autowired
    private ContactMeService contactMeService;


    @GetMapping("/get-home-details")
    public ResponseEntity<SystemResponse> getHomeDetails() {
        List<Home> homeDetails = homeService.getAllHome();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "All Details fetched Successfully!", homeDetails));
    }

    @GetMapping("/get-about")
    public ResponseEntity<SystemResponse> getAbout() {
        List<About> about = aboutService.getAbout();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "All Details fetched Successfully!", about));
    }

    @GetMapping("/get-education-details")
    public ResponseEntity<SystemResponse> getEducation() {
        List<Education> educationList = educationService.getAllEducations();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "All Details fetched Successfully!", educationList));
    }

    @GetMapping("/get-experience")
    public ResponseEntity<SystemResponse> getExperience() {
        List<Experience> experienceList = experienceService.getAllExperience();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "All Details fetched Successfully!", experienceList));
    }

    @GetMapping("/get-skills")
    public ResponseEntity<SystemResponse> getSkills() {
        List<Skills> skillsList = skillsService.getAllSkills();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "All Details fetched Successfully!", skillsList));
    }

    @GetMapping("/get-all-contacted-me")
    public ResponseEntity<SystemResponse> getAllContactedMe() {
        List<ContactMe> contactMeList = contactMeService.getAllMails();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "All Details fetched Successfully!", contactMeList));
    }
}
