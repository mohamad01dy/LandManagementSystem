package com.land.LandManagement.controllers;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.services.LandService;
import com.land.backend.api.LandApi;
import com.land.backend.dto.LandDto;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static com.land.LandManagement.mappers.LandMapper.MAPPER;
import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class LandController implements LandApi {

    @Autowired
    private LandService landService;

    @Override
    public ResponseEntity<LandDto> createLand(LandDto landDto) {
        Land land = MAPPER.map(landDto);
        LandDto createdLand = MAPPER.map(landService.createLand(land));
        return new ResponseEntity<>(createdLand, CREATED);
    }
}
