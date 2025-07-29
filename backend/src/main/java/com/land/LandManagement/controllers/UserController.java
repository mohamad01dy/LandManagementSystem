package com.land.LandManagement.controllers;

import com.land.LandManagement.domain.tables.User;
import com.land.LandManagement.services.UserService;
import com.land.backend.api.UserApi;
import com.land.backend.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static com.land.LandManagement.mappers.UserMapper.MAPPER;

@Controller
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<UserDto> getUserById(Integer id) {
        UserDto userDto = MAPPER.map(userService.getUserById(id));
        return ResponseEntity.ok(userDto);
    }

    @Override
    public ResponseEntity<Void> updateUser(UserDto userDto){
        User user = MAPPER.map(userDto);
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserDto> getUserByToken() {
        UserDto targetUser = MAPPER.map(userService.getUserByToken());
        return  ResponseEntity.ok(targetUser);
    }
}
