package com.land.LandManagement.controllers;

import com.land.backend.api.TestApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test implements TestApi {

    @GetMapping("/test")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Backend is working");
    }

}
