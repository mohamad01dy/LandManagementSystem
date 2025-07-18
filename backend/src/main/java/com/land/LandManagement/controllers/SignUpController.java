package com.land.LandManagement.controllers;

import com.land.LandManagement.domain.tables.User;
import com.land.LandManagement.services.UserService;
import com.land.backend.api.SignUpApi;
import com.land.backend.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static com.land.LandManagement.mappers.UserMapper.MAPPER;
import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class SignUpController implements SignUpApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        User user = MAPPER.map(userDto);
        UserDto createdUser = MAPPER.map(userService.createUser(user));
        return new ResponseEntity<>(createdUser, CREATED);
    }
}
