package com.shivamingale.backend.controller;

import com.shivamingale.backend.dto.HomeDto;
import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.Home;
import com.shivamingale.backend.service.HomeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private HomeService homeService;


    @PostMapping("/update-home")
    public ResponseEntity<SystemResponse> updateHome(@Valid @RequestBody HomeDto homeData) {
        Home updatedHomeData = homeService.updatehome(homeData);
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched Successfully!", updatedHomeData));
    }


}
