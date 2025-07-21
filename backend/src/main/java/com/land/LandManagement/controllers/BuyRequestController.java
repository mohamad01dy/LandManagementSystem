package com.land.LandManagement.controllers;

import com.land.LandManagement.domain.tables.BuyRequest;
import com.land.LandManagement.mappers.BuyRequestMapper;
import com.land.LandManagement.services.BuyRequestService;
import com.land.LandManagement.services.LandService;
import com.land.LandManagement.services.UserService;
import com.land.backend.api.BuyRequestApi;
import com.land.backend.dto.BuyRequestDto;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.land.LandManagement.mappers.BuyRequestMapper.MAPPER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class BuyRequestController implements BuyRequestApi {

    @Autowired
    BuyRequestService buyRequestService;

    @Autowired
    LandService landService;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<BuyRequestDto> createBuyRequest(BuyRequestDto buyRequestDto)
    {
        BuyRequest buyRequest = MAPPER.map(buyRequestDto, userService, landService);
        BuyRequestDto createdRequest = MAPPER.map(buyRequestService.createBuyRequest(buyRequest));
        return new ResponseEntity<>(createdRequest, CREATED);
    }
    @Override
    public ResponseEntity<List<BuyRequestDto>> getSentBuyRequests() {
        List<BuyRequestDto>  sentBuyRequests = buyRequestService.getSentBuyRequests();
        return new ResponseEntity<>(sentBuyRequests, OK);
    }
}
