package com.codecool.solarwatch;

import com.codecool.solarwatch.exception.CityNotFoundException;
import com.codecool.solarwatch.model.GeoLocationReport;
import com.codecool.solarwatch.model.SunriseSunsetApiResponse;
import com.codecool.solarwatch.model.SunriseSunsetReport;
import com.codecool.solarwatch.service.GeocodingService;
import com.codecool.solarwatch.service.SunriseSunsetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SolarWatchServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeocodingService geocodingService;

    @InjectMocks
    private SunriseSunsetService sunriseSunsetService;

    @Test
    void GetCoordinates_ReturnsFirstResult_WhenCityExists() {
        GeoLocationReport[] mockResponse = new GeoLocationReport[] {
                new GeoLocationReport(47.497913, 19.040236)
        };

        when(restTemplate.getForObject(any(String.class), eq(GeoLocationReport[].class))).thenReturn(mockResponse);

        GeoLocationReport result = geocodingService.getCoordinates("Budapest");

        assertEquals(47.497913, result.lat());
        assertEquals(19.040236, result.lon());
    }

    @Test
    void GetCoordinates_ThrowException_WhenCityNotExists() {
        GeoLocationReport[] mockResponse = null;

        when(restTemplate.getForObject(any(String.class), eq(GeoLocationReport[].class))).thenReturn(mockResponse);

        assertThrows(CityNotFoundException.class, () -> geocodingService.getCoordinates("Nonexistent city"));
    }

    @Test
    void GetSunriseSunsetReport_ReturnRightResult() {
        SunriseSunsetApiResponse mockResponse = new SunriseSunsetApiResponse("2026-07-23T05:12:34+02:00","2026-07-23T20:45:10+02:00");

        when(restTemplate.getForObject(any(String.class), eq(SunriseSunsetApiResponse.class))).thenReturn(mockResponse);

        SunriseSunsetReport result = sunriseSunsetService.getSunriseSunsetReport(47.497913, 19.040236, LocalDate.parse("2026-07-23"), "Budapest");

        assertEquals("Budapest", result.city());
        assertEquals(LocalDate.parse("2026-07-23"), result.date());
        assertEquals(LocalTime.of(5, 12, 34), result.sunrise());
        assertEquals(LocalTime.of(20, 45, 10), result.sunset());
    }
}