package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.BuyRequest;
import com.land.LandManagement.domain.tables.OwnershipHistory;
import com.land.LandManagement.domain.tables.User;
import com.land.LandManagement.mappers.BuyRequestMapper;
import com.land.LandManagement.repositories.BuyRequestRepository;
import com.land.LandManagement.repositories.LandRepository;
import com.land.LandManagement.repositories.UserRepository;
import com.land.backend.dto.BuyRequestDto;
import com.land.backend.dto.UpdateRequestStatusRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.land.LandManagement.domain.tables.Land;

import java.util.List;

@Service
public class BuyRequestService {

    @Autowired
    BuyRequestRepository buyRequestRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OwnershipHistoryService ownershipHistoryService;
    @Autowired
    LandRepository landRepository;

    public BuyRequest createBuyRequest(BuyRequest buyRequest) {
        //requestId is auto generated
        buyRequest.setRequestId(null);
        //Frontend will only send landId and status
        // Get buyer email from the token

        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User buyer = userRepository.findByEmail(authenticatedEmail).get();
        User seller = buyRequest.getLand().getOwner();

        buyRequest.setBuyer(buyer);
        buyRequest.setSeller(seller);


        return buyRequestRepository.save(buyRequest);
    }

    public List<BuyRequestDto> getSentBuyRequests() {

        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<BuyRequestDto> sentBuyRequests = BuyRequestMapper.MAPPER.toDto(user.getSentBuyRequests());
        return sentBuyRequests;
    }


    public List<BuyRequestDto> getReceivedBuyRequests() {

        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<BuyRequestDto> receivedBuyRequests = BuyRequestMapper.MAPPER.toDto(user.getReceivedBuyRequests());
        return receivedBuyRequests;
    }

    public void updateRequestStatus(Integer id, UpdateRequestStatusRequestDto updateRequestStatusRequestDto) {
        BuyRequest buyRequest = buyRequestRepository.getReferenceById(id);
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

//        if (!buyRequest.getSeller().getEmail().equals(authenticatedEmail)){
//            throw new AccessDeniedException("You are not allowed to answer other users' requests.");
//        }

        buyRequest.setStatus(updateRequestStatusRequestDto.getStatus().name());
        if (updateRequestStatusRequestDto.getStatus().name().equals("ACCEPTED")) {
            OwnershipHistory ownershipHistory = ownershipHistoryService.createOwnershipHistory(buyRequest);
            Land land = buyRequest.getLand();
            land.setOwner(buyRequest.getBuyer());
            landRepository.save(land);
        }
        else {buyRequestRepository.save(buyRequest); }
    }
}
