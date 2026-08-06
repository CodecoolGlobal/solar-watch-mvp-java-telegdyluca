package com.codecool.solarwatch;

import com.codecool.solarwatch.controller.SunriseSunsetController;
import com.codecool.solarwatch.controller.SunriseSunsetControllerAdvice;
import com.codecool.solarwatch.exception.CityNotFoundException;
import com.codecool.solarwatch.model.dto.GeoLocationReport;
import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.service.GeocodingService;
import com.codecool.solarwatch.service.SunriseSunsetService;
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
public class SolarWatchControllerTests {

    @Mock
    GeocodingService geocodingService;

    @Mock
    SunriseSunsetService sunriseSunsetService;

    @InjectMocks
    private SunriseSunsetController controller;

    @Test
    void GetSunriseSunset_ReturnRightResult() {
        GeoLocationReport mockLocation = new GeoLocationReport(47.497913, 19.040236);
        SunriseSunsetReport mockReport = new SunriseSunsetReport(
                "Budapest", LocalDate.parse("2026-07-23"),
                LocalTime.of(5, 12, 34), LocalTime.of(20, 45, 10));

        when(geocodingService.getCoordinates("Budapest")).thenReturn(mockLocation);
        when(sunriseSunsetService.getSunriseSunsetReport(
                mockLocation.lat(), mockLocation.lon(), LocalDate.parse("2026-07-23"), "Budapest"))
                .thenReturn(mockReport);

        SunriseSunsetReport result = controller.getSunriseSunset("Budapest", LocalDate.parse("2026-07-23"));

        assertEquals(mockReport, result);
    }

    @Test
    void CityNotFoundExceptionHandler_ReturnsCorrectMessage() {
        SunriseSunsetControllerAdvice advice = new SunriseSunsetControllerAdvice();
        CityNotFoundException exception = new CityNotFoundException();

        String result = advice.cityNotFoundExceptionHandler(exception);

        assertEquals("City not found", result);
    }
}
