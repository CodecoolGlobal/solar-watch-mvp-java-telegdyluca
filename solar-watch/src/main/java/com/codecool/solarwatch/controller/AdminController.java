package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.payload.CityRequest;
import com.codecool.solarwatch.model.payload.SunriseSunsetRequest;
import com.codecool.solarwatch.service.sql.CityService;
import com.codecool.solarwatch.service.sql.SunriseSunsetTimesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CityService cityService;
    private final SunriseSunsetTimesService sunriseSunsetTimesService;

    public AdminController(CityService cityService, SunriseSunsetTimesService sunriseSunsetTimesService) {
        this.cityService = cityService;
        this.sunriseSunsetTimesService = sunriseSunsetTimesService;
    }

    @PostMapping("/city")
    public ResponseEntity<Void> createCity(@RequestBody CityRequest request) {
        cityService.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<Void> editCity(@PathVariable Long id, @RequestBody CityRequest request) {
        cityService.editCity(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/sunrise-sunset")
    public ResponseEntity<Void> createSunriseSunset(@RequestBody SunriseSunsetRequest request) {
        sunriseSunsetTimesService.createSunriseSunsetTime(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/sunrise-sunset/{id}")
    public ResponseEntity<Void> editSunriseSunset(@PathVariable Long id, @RequestBody SunriseSunsetRequest request) {
        sunriseSunsetTimesService.editSunriseSunsetTime(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sunrise-sunset/{id}")
    public ResponseEntity<Void> deleteSunriseSunsetTime(@PathVariable Long id) {
        sunriseSunsetTimesService.deleteSunriseSunsetTime(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
