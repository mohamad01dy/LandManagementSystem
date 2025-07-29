package com.land.LandManagement.mappers;

import com.land.LandManagement.domain.tables.BuyRequest;
import com.land.LandManagement.services.LandService;
import com.land.LandManagement.services.UserService;
import com.land.backend.dto.BuyRequestDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, LandMapper.class})
public interface BuyRequestMapper {
    BuyRequestMapper MAPPER = Mappers.getMapper(BuyRequestMapper.class);


    @Mapping(target = "buyerId", expression = "java(entity.getBuyer().getId())")
    @Mapping(target = "sellerId", expression = "java(entity.getSeller() == null ? null : entity.getSeller().getId())")
    @Mapping(target = "landId", expression = "java(entity.getLand().getId())")
    BuyRequestDto map(BuyRequest entity);

    //expression = "java(userService.getUserById(dto.getBuyerId()))"
    //expression = "java(userService.getUserById(dto.getSellerId()))"
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "land", expression = "java(landService.getLandById(dto.getLandId()))")
    BuyRequest map(BuyRequestDto dto, @Context UserService userService, @Context LandService landService);

    List<BuyRequest> toDomain(List<BuyRequestDto> dto, @Context UserService userService, @Context LandService landService);
    List<BuyRequestDto> toDto(List<BuyRequest> entity);
}
