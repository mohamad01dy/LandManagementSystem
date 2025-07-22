package com.land.LandManagement.mappers;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.services.OwnershipHistoryService;
import com.land.LandManagement.services.UserService;
import com.land.backend.dto.LandDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class, BuyRequestMapper.class})
public interface LandMapper {
    LandMapper MAPPER = Mappers.getMapper(LandMapper.class);

    @Mapping(target = "ownershipHistoryIds",
        expression = "java(entity.getOwnershipHistory() == null ? null : entity.getOwnershipHistory().stream().map(com.land.LandManagement.domain.tables.OwnershipHistory::getHistoryId).toList())")
    @Mapping(target = "ownerId",
        expression = "java(entity.getOwner() == null ? null : entity.getOwner().getId())")
    LandDto map (Land entity);


    @Mapping(target = "owner", expression = "java(dto.getOwnerId() == null ? null : userService.getUserById(dto.getOwnerId()))")
    @Mapping(target = "ownershipHistory", expression = "java(dto.getOwnershipHistoryIds() == null ? null : dto.getOwnershipHistoryIds().stream().map(ownershipHistoryService::getOwnershipHistoryById).toList())")
    Land map (LandDto dto, @Context UserService userService, @Context OwnershipHistoryService ownershipHistoryService);
}
