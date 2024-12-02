package com.shivamingale.backend.controller;


import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.model.User;
import com.shivamingale.backend.service.JwtService;
import com.shivamingale.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final JwtService jwtUtil;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, JwtService jwtUtil, ModelMapper modelMapper) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    // Endpoint to validate user based on the JWT token
    @GetMapping("/validate-user")
    public ResponseEntity<SystemResponse> validateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new SystemResponse<>(false, "User is not authenticated"));
        }


        User userDetails = modelMapper.map(authentication.getPrincipal(), User.class);

        logger.info("User {} {} validated Successfully!", userDetails.getFirstName(), userDetails.getLastName());
        return ResponseEntity.ok().body(new SystemResponse<>(true, "User Authenticated Successfully!", userDetails));
    }
}
