package com.shivamingale.backend.service;


import com.shivamingale.backend.exception.AppException;
import com.shivamingale.backend.model.Home;
import com.shivamingale.backend.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeService {
    @Autowired
    private HomeRepository homeRepository;

    public List<Home> getAllHome() {
        return homeRepository.findAll();
    }

}
