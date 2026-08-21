package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.model.entity.Role;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimeEntity;
import com.codecool.solarwatch.model.entity.UserEntity;
import com.codecool.solarwatch.model.payload.JwtResponse;
import com.codecool.solarwatch.model.payload.UserRequest;
import com.codecool.solarwatch.security.jwt.JwtUtils;
import com.codecool.solarwatch.service.sql.CityService;
import com.codecool.solarwatch.service.sql.SunriseSunsetTimesService;
import com.codecool.solarwatch.service.sql.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final CityService cityService;
    private final SunriseSunsetTimesService sunriseSunsetTimesService;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserController(CityService cityService, SunriseSunsetTimesService sunriseSunsetTimesService, UserService userService,
                          JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.cityService = cityService;
        this.sunriseSunsetTimesService = sunriseSunsetTimesService;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> createUser(@RequestBody UserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(userService.getPassword(request));
        userEntity.setRoles(Set.of(Role.ROLE_USER));
        userService.createUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }

    @GetMapping("/sunrise-sunset")
    public SunriseSunsetReport getSunriseSunset(@RequestParam String city, @RequestParam LocalDate date) {

        CityEntity cityEntity = cityService.getCityByName(city);
        SunriseSunsetTimeEntity times = sunriseSunsetTimesService.getSunriseSunsetByCityAndDate(cityEntity, date);

        return new SunriseSunsetReport(cityEntity.getName(), times.getDate(), times.getSunrise(), times.getSunset());
    }
}
