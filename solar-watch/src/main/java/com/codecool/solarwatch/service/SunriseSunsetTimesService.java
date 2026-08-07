package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimes;
import com.codecool.solarwatch.repository.SunriseSunsetTimesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SunriseSunsetTimesService {

    private final SunriseSunsetTimesRepository sunriseSunsetTimesRepository;
    private final SunriseSunsetService sunriseSunsetService;

    public SunriseSunsetTimesService(SunriseSunsetTimesRepository sunriseSunsetTimesRepository, SunriseSunsetService sunriseSunsetService) {
        this.sunriseSunsetTimesRepository = sunriseSunsetTimesRepository;
        this.sunriseSunsetService = sunriseSunsetService;
    }

    public SunriseSunsetTimes getSunriseSunsetByCityAndDate(City city, LocalDate date) {
        Optional<SunriseSunsetTimes> existingSunriseSunsetTimes = sunriseSunsetTimesRepository.findByCityAndDate(city, date);

        if (existingSunriseSunsetTimes.isEmpty()) {
            SunriseSunsetReport report = sunriseSunsetService.getSunriseSunsetReport(city.getLatitude(), city.getLongitude(), date, city.getName());
            SunriseSunsetTimes sunriseSunsetTimes = new SunriseSunsetTimes();
            sunriseSunsetTimes.setCity(city);
            sunriseSunsetTimes.setDate(date);
            sunriseSunsetTimes.setSunrise(report.sunrise());
            sunriseSunsetTimes.setSunset(report.sunset());

            sunriseSunsetTimesRepository.save(sunriseSunsetTimes);
            return sunriseSunsetTimes;

        } else {
            return existingSunriseSunsetTimes.get();
        }
    }
}
