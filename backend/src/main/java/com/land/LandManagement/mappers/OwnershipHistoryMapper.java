package com.land.LandManagement.mappers;

import com.land.LandManagement.domain.tables.OwnershipHistory;
import com.land.LandManagement.services.LandService;
import com.land.LandManagement.services.UserService;
import com.land.backend.dto.OwnershipHistoryDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OwnershipHistoryMapper {

    OwnershipHistoryMapper MAPPER = Mappers.getMapper(OwnershipHistoryMapper.class);

    @Mapping(target = "owner", expression = "java(userService.getUserById(dto.getOwnerId()))")
    @Mapping(target = "land", expression = "java(landService.getLandById(dto.getLandId()))")
    OwnershipHistory map(OwnershipHistoryDto dto, @Context UserService userService, @Context LandService landService);

    @Mapping(target = "ownerId", expression = "java(entity.getOwner().getId())")
    @Mapping(target = "landId", expression = "java(entity.getLand().getId())")
    OwnershipHistoryDto map(OwnershipHistory entity);

    List<OwnershipHistory> toDomain(List<OwnershipHistoryDto> dto, @Context UserService userService, @Context LandService landService);
    List<OwnershipHistoryDto> toDto(List<OwnershipHistory> entity);

}
