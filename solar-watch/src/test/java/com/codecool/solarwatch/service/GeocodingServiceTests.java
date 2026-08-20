package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.CityNotFoundException;
import com.codecool.solarwatch.model.dto.GeoLocationReport;
import com.codecool.solarwatch.service.api.GeocodingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeocodingServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeocodingService geocodingService;

    @Test
    void getCoordinates_ReturnsFirstResult_WhenCityExists() {
        GeoLocationReport[] mockResponse = new GeoLocationReport[] {
                new GeoLocationReport(47.497913, 19.040236, "HUN", "Pest")
        };

        when(restTemplate.getForObject(any(String.class), eq(GeoLocationReport[].class))).thenReturn(mockResponse);

        GeoLocationReport result = geocodingService.getCoordinates("Budapest");

        assertEquals(47.497913, result.lat());
        assertEquals(19.040236, result.lon());
    }

    @Test
    void getCoordinates_ThrowException_WhenCityNotExists() {
        GeoLocationReport[] mockResponse = null;

        when(restTemplate.getForObject(any(String.class), eq(GeoLocationReport[].class))).thenReturn(mockResponse);

        assertThrows(CityNotFoundException.class, () -> geocodingService.getCoordinates("Nonexistent city"));
    }
}