package com.shivamingale.backend.service;


import com.shivamingale.backend.dto.HomeDto;
import com.shivamingale.backend.model.Home;
import com.shivamingale.backend.repository.HomeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeService {
    @Autowired
    private HomeRepository homeRepository;
    private ModelMapper modelMapper;

    public List<Home> getAllHome() {
        return homeRepository.findAll();
    }

    public Home updatehome(HomeDto homeData) {
        Home home = modelMapper.map(homeData, Home.class);
        return homeRepository.save(home);
    }

}
