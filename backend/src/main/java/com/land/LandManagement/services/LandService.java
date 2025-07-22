package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.domain.tables.OwnershipHistory;
import com.land.LandManagement.repositories.LandRepository;
import com.land.LandManagement.repositories.OwnershipHistoryRepository;
import com.land.backend.dto.LandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandService {

    @Autowired
    LandRepository landRepository;
    @Autowired
    OwnershipHistoryRepository ownershipHistoryRepository;

    public Land getLandById(Integer id) {
        return landRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Land not found"));
    }

    public List<OwnershipHistory> getOrderedOwnershipHistory(Integer id) {
        return ownershipHistoryRepository.findByLandIdOrderByOrderNumber(id);
    }

    public Land createLand(Land land) {
        //Id auto-generated
        land.setId(null);
        return landRepository.save(land);
    }
}
