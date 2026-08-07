package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimes;
import com.codecool.solarwatch.service.CityService;
import com.codecool.solarwatch.service.SunriseSunsetTimesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SunriseSunsetController {

    private final CityService cityService;
    private final SunriseSunsetTimesService sunriseSunsetTimesService;

    public SunriseSunsetController(CityService cityService, SunriseSunsetTimesService sunriseSunsetTimesService) {
        this.cityService = cityService;
        this.sunriseSunsetTimesService = sunriseSunsetTimesService;
    }

    @GetMapping("/sunrise-sunset")
    public SunriseSunsetReport getSunriseSunset(@RequestParam String city, @RequestParam LocalDate date) {

        City cityEntity = cityService.getCityByName(city);
        SunriseSunsetTimes times = sunriseSunsetTimesService.getSunriseSunsetByCityAndDate(cityEntity, date);

        return new SunriseSunsetReport(cityEntity.getName(), times.getDate(), times.getSunrise(), times.getSunset());
    }
}
