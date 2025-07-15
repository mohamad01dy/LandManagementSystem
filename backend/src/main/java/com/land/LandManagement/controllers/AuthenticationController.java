package com.land.LandManagement.controllers;

import com.land.LandManagement.security.UserDetailsServiceImpl;
import com.land.LandManagement.services.JWTUtil;
import com.land.backend.api.AuthApi;
import com.land.backend.dto.LoginRequestDto;
import com.land.backend.dto.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthApi { // or LoginApi

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public ResponseEntity<LoginResponseDto> login(@Valid LoginRequestDto request) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            authManager.authenticate(authInputToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String token = jwtUtil.generateToken(userDetails.getUsername());

            LoginResponseDto response = new LoginResponseDto().token(token);
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }
}