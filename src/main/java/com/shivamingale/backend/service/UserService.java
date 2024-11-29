package com.shivamingale.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivamingale.backend.dto.LoginDTO;
import com.shivamingale.backend.dto.UserDTO;
import com.shivamingale.backend.exception.AppException;
import com.shivamingale.backend.model.User;
import com.shivamingale.backend.repository.UserRepository;
import com.shivamingale.backend.util.RedisKeys;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Value("${redis.keys.home.admin}")
    private String redisKeyAdmin;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RedisService redisService;
    private ModelMapper modelMapper;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RedisService redisService, RedisKeys redisKeys) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
    }


    public User registerUser(UserDTO userDTO) {
        // Check if the username, email, or mobile already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw AppException.alreadyExists("Username");
        }

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw AppException.alreadyExists(userDTO.getEmail());
        }

        if (userRepository.findByMobile(userDTO.getMobile()).isPresent()) {
            throw AppException.alreadyExists(userDTO.getMobile());
        }
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }


    public User loginUser(LoginDTO loginDetails) {
        User user = null;

        if (loginDetails.getUsername() != null && loginDetails.getUsername().length() > 1) {
            user = userRepository.findByUsername(loginDetails.getUsername()).orElseThrow(() -> AppException.notFound(loginDetails.getUsername()));
        } else if (loginDetails.getEmail() != null && loginDetails.getEmail().length() > 1) {
            user = userRepository.findByEmail(loginDetails.getEmail()).orElseThrow(() -> AppException.notFound(loginDetails.getEmail()));
        } else if (loginDetails.getMobile() != null && loginDetails.getMobile().length() > 1) {
            user = userRepository.findByMobile(loginDetails.getMobile()).orElseThrow(() -> AppException.notFound(loginDetails.getMobile()));
        } else {
            throw AppException.notFound("No Username / Email / Mobile received!");
        }
        if (passwordEncoder.matches(loginDetails.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw AppException.authenticationFailed("Invalid password");
        }
    }


}
