package com.land.LandManagement.mappers;

import com.land.LandManagement.domain.tables.User;
import com.land.backend.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto map (User domain);
    User map (UserDto dto);
}
