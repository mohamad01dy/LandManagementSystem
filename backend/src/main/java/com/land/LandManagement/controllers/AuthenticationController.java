package com.land.LandManagement.controllers;

import com.land.LandManagement.security.UserDetailsServiceImpl;
import com.land.LandManagement.services.JWTUtil;
import com.land.backend.api.AuthApi;
import com.land.backend.dto.LoginRequestDto;
import com.land.backend.dto.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
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
            String accessToken = jwtUtil.generateToken(userDetails.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

            // Set refresh token in HttpOnly cookie
            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(false) // true in production
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60) // 7 days
                    .sameSite("Lax")
                    .build();

            LoginResponseDto response = new LoginResponseDto().token(accessToken);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<LoginResponseDto> refreshAccessToken(
            @CookieValue(value = "refreshToken", required = false) String refreshToken) {
        if (refreshToken == null || !jwtUtil.isTokenValid(refreshToken)) {
            return ResponseEntity.status(401).build();
        }

        String username = jwtUtil.extractUsername(refreshToken);
        String newAccessToken = jwtUtil.generateToken(username);
        LoginResponseDto response = new LoginResponseDto().token(newAccessToken);

        return ResponseEntity.ok(response);
    }
}