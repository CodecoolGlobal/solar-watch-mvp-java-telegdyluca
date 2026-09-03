package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.SunriseSunsetApiResponse;
import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.service.api.SunriseSunsetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SunriseSunsetServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SunriseSunsetService sunriseSunsetService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(sunriseSunsetService, "sunriseUrl", "https://api.sunrise-sunset.org/v2?lat=%s&lng=%s&date=%s");
    }

    @Test
    void getSunriseSunsetReport_ReturnRightResult() {
        SunriseSunsetApiResponse mockResponse = new SunriseSunsetApiResponse("2026-07-23T05:12:34+02:00", "2026-07-23T20:45:10+02:00");

        when(restTemplate.getForObject(any(String.class), eq(SunriseSunsetApiResponse.class))).thenReturn(mockResponse);

        SunriseSunsetReport result = sunriseSunsetService.getSunriseSunsetReport(47.497913, 19.040236, LocalDate.parse("2026-07-23"), "Budapest");

        assertEquals("Budapest", result.city());
        assertEquals(LocalDate.parse("2026-07-23"), result.date());
        assertEquals(LocalTime.of(5, 12, 34), result.sunrise());
        assertEquals(LocalTime.of(20, 45, 10), result.sunset());
    }
}