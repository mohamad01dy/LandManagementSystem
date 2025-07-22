package com.land.LandManagement.controllers;

import com.land.LandManagement.mappers.OwnershipHistoryMapper;
import com.land.LandManagement.services.OwnershipHistoryService;
import com.land.backend.api.OwnershipHistoryApi;
import com.land.backend.dto.OwnershipHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.land.LandManagement.mappers.OwnershipHistoryMapper.MAPPER;

@Controller
public class OwnershipHistoryController implements OwnershipHistoryApi {

    @Autowired
    OwnershipHistoryService ownershipHistoryService;

    @Override
    public ResponseEntity<OwnershipHistoryDto> getOwnershipHistoryById(Integer id) {
        OwnershipHistoryDto createdHistory = MAPPER.map(ownershipHistoryService.getOwnershipHistoryById(id));

        return ResponseEntity.ok(createdHistory);
    }

}
