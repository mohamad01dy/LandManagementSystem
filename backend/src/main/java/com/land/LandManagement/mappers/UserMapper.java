package com.land.LandManagement.mappers;

import com.land.LandManagement.domain.tables.User;
import com.land.backend.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.land.LandManagement.domain.tables.Land;




@Mapper(uses = {LandMapper.class, BuyRequestMapper.class})
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "landIds",
        expression = "java(entity.getLands() == null ? null : entity.getLands().stream().map(com.land.LandManagement.domain.tables.Land::getId).toList())")
    @Mapping(target = "ownershipHistoryIds",
        expression = "java(entity.getOwnershipHistory() == null ? null : entity.getOwnershipHistory().stream().map(com.land.LandManagement.domain.tables.OwnershipHistory::getHistoryId).toList())")
    @Mapping(target = "sentBuyRequestIds",
        expression = "java(entity.getSentBuyRequests() == null ? null : entity.getSentBuyRequests().stream().map(com.land.LandManagement.domain.tables.BuyRequest::getRequestId).toList())")
    @Mapping(target = "receivedBuyRequestIds",
        expression = "java(entity.getReceivedBuyRequests() == null ? null : entity.getReceivedBuyRequests().stream().map(com.land.LandManagement.domain.tables.BuyRequest::getRequestId).toList())")
    UserDto map (User entity);

    // For reverse mapping, you need to fetch entities from their IDs
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lands", ignore = true) // Will be set manually
    @Mapping(target = "ownershipHistory", ignore = true)
    @Mapping(target = "sentBuyRequests", ignore = true)
    @Mapping(target = "receivedBuyRequests", ignore = true)
    User map (UserDto dto);
}
