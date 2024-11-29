package com.shivamingale.backend.service;


import com.shivamingale.backend.model.About;
import com.shivamingale.backend.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AboutService {

    @Autowired
    private AboutRepository aboutRepository;

    public List<About> getAbout() {
        return aboutRepository.findAll();
    }

}
