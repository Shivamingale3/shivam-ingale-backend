package com.shivamingale.backend.controller;


import com.shivamingale.backend.dto.LoginDTO;
import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.dto.UserDTO;
import com.shivamingale.backend.model.User;
import com.shivamingale.backend.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<SystemResponse> loginUser(@RequestBody @Valid LoginDTO loginDetails) {
        User userDetails = userService.loginUser(loginDetails);
        logger.info("User {} {} logged in Successfully!", userDetails.getFirstName(), userDetails.getLastName());
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Login Successful!", userDetails));
    }

    @PostMapping("/register")
    public ResponseEntity<SystemResponse> registerUser(@RequestBody @Valid UserDTO userDetails) {
        User registeredUser = userService.registerUser(userDetails);
        logger.info("User {} {} registered Successfully!", registeredUser.getFirstName(), registeredUser.getLastName());
        return ResponseEntity.ok().body(new SystemResponse<>(true, ("User " + registeredUser.getFirstName() + " " + registeredUser.getLastName() + " registered Successfully!"), registeredUser));
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<SystemResponse> sendForgotPasswordEmail(@RequestBody String email) {

    }
}
