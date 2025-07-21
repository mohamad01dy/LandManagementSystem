package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.BuyRequest;
import com.land.LandManagement.domain.tables.User;
import com.land.LandManagement.mappers.BuyRequestMapper;
import com.land.LandManagement.repositories.BuyRequestRepository;
import com.land.LandManagement.repositories.UserRepository;
import com.land.backend.dto.BuyRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyRequestService {

    @Autowired
    BuyRequestRepository buyRequestRepository;
    @Autowired
    UserRepository userRepository;

    public BuyRequest createBuyRequest(BuyRequest buyRequest) {
        //Frontend will only send landId and status
        //requestId is auto generated
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
}
