package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.payload.JwtResponse;
import com.codecool.solarwatch.model.payload.UserRequest;
import com.codecool.solarwatch.service.sql.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest request) {

        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest request) {

        JwtResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
