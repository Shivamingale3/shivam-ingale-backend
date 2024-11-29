package com.shivamingale.backend.controller;


import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.Skills;
import com.shivamingale.backend.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {
    @Autowired
    private SkillsService skillsService;

    @GetMapping("/get-all")
    public ResponseEntity<SystemResponse> getAllSkills() {
        List<Skills> skillsInfo = skillsService.getAllSkills();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched Successfully!", skillsInfo));
    }

}
