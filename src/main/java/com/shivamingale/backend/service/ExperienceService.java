package com.shivamingale.backend.service;


import com.shivamingale.backend.model.Experience;
import com.shivamingale.backend.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> getAllExperience() {
        return experienceRepository.findAll();
    }
}
