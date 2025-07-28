package com.land.LandManagement.controllers;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.domain.tables.OwnershipHistory;
import com.land.LandManagement.mappers.LandMapper;
import com.land.LandManagement.mappers.OwnershipHistoryMapper;
import com.land.LandManagement.services.LandService;
import com.land.LandManagement.services.OwnershipHistoryService;
import com.land.LandManagement.services.UserService;
import com.land.backend.api.LandApi;
import com.land.backend.dto.LandDto;
import com.land.backend.dto.OwnershipHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.land.LandManagement.mappers.LandMapper.MAPPER;

@Controller
public class LandController implements LandApi {

    @Autowired
    LandService landService;
    @Autowired
    UserService userService;
    @Autowired
    OwnershipHistoryService ownershipHistoryService;

    @Override
    public ResponseEntity<List<OwnershipHistoryDto>> getOrderedOwnershipHistory(Integer id) {
        List<OwnershipHistoryDto> history = OwnershipHistoryMapper.MAPPER.toDto(landService.getOrderedOwnershipHistory(id));

        return  ResponseEntity.ok(history);
    }

    @Override
    public ResponseEntity<LandDto> createLand(LandDto landDto) {
        Land land = LandMapper.MAPPER.map(landDto, userService, ownershipHistoryService);
        LandDto createdLand = LandMapper.MAPPER.map(landService.createLand(land));
        return  ResponseEntity.ok(createdLand);
    }

    @Override
    public ResponseEntity<List<LandDto>> getMyLands() {
        List<LandDto> myLands = MAPPER.toDto(landService.getMyLands());
        return ResponseEntity.ok(myLands);
    }
}
