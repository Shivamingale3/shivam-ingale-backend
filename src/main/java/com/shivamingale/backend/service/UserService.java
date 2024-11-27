package com.shivamingale.backend.service;

import com.shivamingale.backend.dto.LoginDTO;
import com.shivamingale.backend.dto.UserDTO;
import com.shivamingale.backend.exception.AppException;
import com.shivamingale.backend.model.User;
import com.shivamingale.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw AppException.alreadyExists("Username"); //Username already exists
        }

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw AppException.alreadyExists(userDTO.getEmail()); //Email already exists
        }

        if (userRepository.findByMobile(userDTO.getMobile()).isPresent()) {
            throw AppException.alreadyExists(userDTO.getMobile()); //Mobile already exists
        }

        // Map DTO to User
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());  // Ensure role is set
        user.setFirstName(userDTO.getFirstName());  // Ensure firstName is set
        user.setLastName(userDTO.getLastName());  // Ensure lastName is set

        User result = userRepository.save(user);
        return result;
    }


    public User loginUser(LoginDTO loginDetails) {
        User user = null;

        if (loginDetails.getUsername() != null && loginDetails.getUsername().length() > 1) {
            user = userRepository.findByUsername(loginDetails.getUsername())
                    .orElseThrow(() -> AppException.notFound(loginDetails.getUsername()));
        } else if (loginDetails.getEmail() != null && loginDetails.getEmail().length() > 1) {
            user = userRepository.findByEmail(loginDetails.getEmail())
                    .orElseThrow(() -> AppException.notFound(loginDetails.getEmail()));
        } else if (loginDetails.getMobile() != null && loginDetails.getMobile().length() > 1) {
            user = userRepository.findByMobile(loginDetails.getMobile())
                    .orElseThrow(() -> AppException.notFound(loginDetails.getMobile()));
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
