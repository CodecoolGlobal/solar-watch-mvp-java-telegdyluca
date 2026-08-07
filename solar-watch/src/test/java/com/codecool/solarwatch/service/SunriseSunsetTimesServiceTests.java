package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimes;
import com.codecool.solarwatch.repository.SunriseSunsetTimesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SunriseSunsetTimesServiceTests {

    @Mock
    private SunriseSunsetTimesRepository sunriseSunsetTimesRepository;

    @Mock
    private SunriseSunsetService sunriseSunsetService;

    @InjectMocks
    private SunriseSunsetTimesService sunriseSunsetTimesService;

    @Test
    void getSunriseSunsetByCityAndDate_CallsSunriseSunsetServiceAndSaves_WhenSunriseSunsetTimeNotInDatabase() {
        City mockCity = new City();
        mockCity.setName("Budapest");
        mockCity.setLatitude(47.497913);
        mockCity.setLongitude(19.040236);
        SunriseSunsetReport mockReport = new SunriseSunsetReport("Budapest", LocalDate.parse("2026-08-07"), LocalTime.of(5, 12, 34), LocalTime.of(20, 45, 10));

        when(sunriseSunsetTimesRepository.findByCityAndDate(mockCity, LocalDate.parse("2026-08-07"))).thenReturn(Optional.empty());
        when(sunriseSunsetService.getSunriseSunsetReport(47.497913, 19.040236, LocalDate.parse("2026-08-07"), "Budapest")).thenReturn(mockReport);
        when(sunriseSunsetTimesRepository.save(any(SunriseSunsetTimes.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SunriseSunsetTimes result = sunriseSunsetTimesService.getSunriseSunsetByCityAndDate(mockCity, LocalDate.parse("2026-08-07"));

        assertEquals("Budapest", result.getCity().getName());
    }
}
