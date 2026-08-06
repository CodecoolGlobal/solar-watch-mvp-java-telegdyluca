package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.dto.GeoLocationReport;
import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.service.GeocodingService;
import com.codecool.solarwatch.service.SunriseSunsetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SunriseSunsetController {

    private final GeocodingService geocodingService;
    private final SunriseSunsetService sunriseSunsetService;

    public SunriseSunsetController(GeocodingService geocodingService, SunriseSunsetService sunriseSunsetService) {
        this.geocodingService = geocodingService;
        this.sunriseSunsetService = sunriseSunsetService;
    }

    @GetMapping("/sunrise-sunset")
    public SunriseSunsetReport getSunriseSunset(@RequestParam String city, @RequestParam LocalDate date) {

        GeoLocationReport locationCoordinates = geocodingService.getCoordinates(city);
        return sunriseSunsetService.getSunriseSunsetReport(locationCoordinates.lat(), locationCoordinates.lon(), date, city);
    }
}
