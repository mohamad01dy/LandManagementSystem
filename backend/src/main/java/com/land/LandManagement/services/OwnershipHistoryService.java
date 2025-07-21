package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.OwnershipHistory;
import com.land.LandManagement.repositories.OwnershipHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnershipHistoryService {

    @Autowired
    OwnershipHistoryRepository ownershipHistoryRepository;

    public OwnershipHistory getHistoryById(Integer id) {
        return ownershipHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OwnershipHistory not found"));
    }

}
