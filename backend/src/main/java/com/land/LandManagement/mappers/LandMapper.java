package com.land.LandManagement.mappers;
import com.land.LandManagement.domain.tables.Land;
import com.land.backend.dto.LandDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LandMapper {
    LandMapper MAPPER = Mappers.getMapper(LandMapper.class);

    LandDto map (Land land);
    Land map (LandDto dto);
}
