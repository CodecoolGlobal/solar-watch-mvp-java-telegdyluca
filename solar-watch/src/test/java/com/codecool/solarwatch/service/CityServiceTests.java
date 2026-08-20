package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.GeoLocationReport;
import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.repository.CityRepository;
import com.codecool.solarwatch.service.api.GeocodingService;
import com.codecool.solarwatch.service.sql.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceTests {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private GeocodingService geocodingService;

    @InjectMocks
    private CityService cityService;

    @Test
    void getCityByName_CallsGeocodingServiceAndSaves_WhenCityNotInDatabase() {
        GeoLocationReport mockReport = new GeoLocationReport(47.497913, 19.040236, "HUN", "Pest");

        when(cityRepository.findByName("Budapest")).thenReturn(Optional.empty());
        when(geocodingService.getCoordinates("Budapest")).thenReturn(mockReport);
        when(cityRepository.save(any(CityEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CityEntity result = cityService.getCityByName("Budapest");

        assertEquals("Budapest", result.getName());
        assertEquals(47.497913, result.getLatitude());
        assertEquals(19.040236, result.getLongitude());
    }
}