package com.shivamingale.backend.controller;

import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.Home;
import com.shivamingale.backend.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/get-all")
    public ResponseEntity<SystemResponse> getAllHomeData() {
        List<Home> homeInfo = homeService.getAllHome();
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Fetched SuccessFully!", homeInfo));
    }


}
