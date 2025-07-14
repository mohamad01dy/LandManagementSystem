package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.repositories.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandService {

    @Autowired
    private LandRepository landRepository;
    public Land createLand(Land land){
        return landRepository.save(land);
    }
}
