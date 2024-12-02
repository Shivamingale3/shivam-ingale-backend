package com.shivamingale.backend.service;

import com.shivamingale.backend.dto.LoginDTO;
import com.shivamingale.backend.dto.UserDTO;
import com.shivamingale.backend.exception.AppException;
import com.shivamingale.backend.model.User;
import com.shivamingale.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Value("${redis.keys.home.admin}")
    private String redisKeyAdmin;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDetails.getEmail(), loginDetails.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> AppException.notFound("Email not found: " + loginDetails.getEmail()));
        ;
        String token = jwtService.generateToken(user);
        user.setJWTToken(token);
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> AppException.notFound("User by username: " + username));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByUsername(email).orElseThrow(() -> AppException.notFound("User by email: " + email));
    }
}
