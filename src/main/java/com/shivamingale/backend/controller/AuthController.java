package com.shivamingale.backend.controller;

import com.shivamingale.backend.dto.LoginDTO;
import com.shivamingale.backend.dto.SystemResponse;
import com.shivamingale.backend.dto.UserDTO;
import com.shivamingale.backend.model.User;
import com.shivamingale.backend.repository.UserRepository;
import com.shivamingale.backend.service.UserService;
import com.shivamingale.backend.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SystemResponse> register(@Valid @RequestBody UserDTO user) {
        User registeredUser = userService.registerUser(user);
        UserDTO userResponse = new UserDTO();
        userResponse.setUsername(registeredUser.getUsername());
        userResponse.setEmail(registeredUser.getEmail());
        userResponse.setMobile(registeredUser.getMobile());

        return ResponseEntity.ok().body(new SystemResponse<>(true, "User Registered Successfully!", userResponse));
    }


    @PostMapping("/login")
    public ResponseEntity<SystemResponse> login(@Valid @RequestBody LoginDTO loginDetails) {
        User user = userService.loginUser(loginDetails);
        String JwtToken = jwtUtil.generateToken(user.getUsername(), String.join(",", user.getRole()));
        user.setPassword(JwtToken);
        return ResponseEntity.ok().body(new SystemResponse<>(true, "Authenticated Successfully!", user));
    }
}
