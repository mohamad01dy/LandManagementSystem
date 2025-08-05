package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.domain.tables.OwnershipHistory;
import com.land.LandManagement.domain.tables.User;
import com.land.LandManagement.repositories.BuyRequestRepository;
import com.land.LandManagement.repositories.LandRepository;
import com.land.LandManagement.repositories.OwnershipHistoryRepository;
import com.land.LandManagement.repositories.UserRepository;
import com.land.backend.dto.LandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandService {

    @Autowired
    LandRepository landRepository;
    @Autowired
    OwnershipHistoryRepository ownershipHistoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BuyRequestRepository buyRequestRepository;

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

    public List<Land> getMyLands() {
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User targetUser = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return targetUser.getLands();
    }

    public List<Land> getAvailableLands() {
        return landRepository.findAll();
    }

    public void deleteLand(Integer id) {
        if (!landRepository.existsById(id)) {
            throw new RuntimeException("Land not found with id: " + id);
        }
        buyRequestRepository.deleteByLandId(id);
        landRepository.deleteById(id);
    }
}
