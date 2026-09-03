package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.exception.CityNotFoundException;
import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimeEntity;
import com.codecool.solarwatch.service.sql.CityService;
import com.codecool.solarwatch.service.sql.SunriseSunsetTimesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SunriseSunsetControllerTests {

    @Mock
    CityService cityService;

    @Mock
    SunriseSunsetTimesService sunriseSunsetTimesService;

    @InjectMocks
    private AuthController controller;

    @Test
    void getSunriseSunset_ReturnRightResult() {
        CityEntity mockCity = new CityEntity();
        mockCity.setName("Budapest");
        mockCity.setCountry("HUN");
        mockCity.setState("Pest");
        mockCity.setLongitude(19.040236);
        mockCity.setLatitude(47.497913);

        SunriseSunsetTimeEntity sunriseSunsetTimesEntity = new SunriseSunsetTimeEntity();
        sunriseSunsetTimesEntity.setCity(mockCity);
        sunriseSunsetTimesEntity.setDate(LocalDate.parse("2026-07-23"));
        sunriseSunsetTimesEntity.setSunrise(LocalTime.of(5, 12, 34));
        sunriseSunsetTimesEntity.setSunset(LocalTime.of(20, 45, 10));

        when(cityService.getCityByName("Budapest")).thenReturn(mockCity);
        when(sunriseSunsetTimesService.getSunriseSunsetByCityAndDate(mockCity, LocalDate.parse("2026-07-23"))).thenReturn(sunriseSunsetTimesEntity);

        SunriseSunsetReport result = controller.getSunriseSunset("Budapest", LocalDate.parse("2026-07-23"));

        SunriseSunsetReport expectedReport = new SunriseSunsetReport(
                "Budapest", LocalDate.parse("2026-07-23"),
                LocalTime.of(5, 12, 34), LocalTime.of(20, 45, 10));

        assertEquals(expectedReport, result);
    }

    @Test
    void cityNotFoundExceptionHandler_ReturnsCorrectMessage() {
        ControllerAdvice advice = new ControllerAdvice();
        CityNotFoundException exception = new CityNotFoundException();

        String result = advice.cityNotFoundExceptionHandler(exception);

        assertEquals("City not found", result);
    }
}
