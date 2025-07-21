package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.repositories.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandService {

    @Autowired
    LandRepository landRepository;

    public Land getLandById(Integer id) {
        return landRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Land not found"));
    }

}
