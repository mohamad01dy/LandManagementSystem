package com.land.LandManagement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping
    public ResponseEntity<String> testing() {
        return ResponseEntity.ok("Backend is working");
    }

}
