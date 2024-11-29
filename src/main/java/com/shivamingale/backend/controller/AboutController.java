package com.shivamingale.backend.controller;


import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.About;
import com.shivamingale.backend.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @GetMapping("/get-all")
    public ResponseEntity<SystemResponse> getAbout() {
        List<About> aboutInfo = aboutService.getAbout();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched Successfully!", aboutInfo));
    }
}
