package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.BuyRequest;
import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.domain.tables.OwnershipHistory;
import com.land.LandManagement.repositories.OwnershipHistoryRepository;
import com.land.backend.dto.OwnershipHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnershipHistoryService {

    @Autowired
    OwnershipHistoryRepository ownershipHistoryRepository;
    @Autowired
    LandService landService;

    public OwnershipHistory getOwnershipHistoryById(Integer id) {
        return ownershipHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OwnershipHistory not found"));
    }

    public OwnershipHistory createOwnershipHistory(BuyRequest buyRequest) {

        int maxOrder = ownershipHistoryRepository.findMaxOrderNumberByLandId(buyRequest.getLand().getId())
                .orElse(0);

        OwnershipHistory ownershipHistory = new OwnershipHistory();

        //historyId auto-generated
        ownershipHistory.setHistoryId(null);

        ownershipHistory.setOwner(buyRequest.getBuyer());
        ownershipHistory.setLand(buyRequest.getLand());
        ownershipHistory.setOrderNumber(maxOrder+1);

        return ownershipHistoryRepository.save(ownershipHistory);
    }

}
