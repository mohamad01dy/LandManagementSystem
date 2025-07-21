package com.land.LandManagement.mappers;

import com.land.LandManagement.domain.tables.BuyRequest;
import com.land.backend.dto.BuyRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, LandMapper.class})
public interface BuyRequestMapper {
    BuyRequestMapper MAPPER = Mappers.getMapper(BuyRequestMapper.class);

    BuyRequestDto map(BuyRequest domain);
    BuyRequest map(BuyRequestDto dto);

    List<BuyRequest> toDomain(List<BuyRequestDto> dto);
    List<BuyRequestDto> toDto(List<BuyRequest> domain);
}
