package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.GeoLocationReport;
import com.codecool.solarwatch.model.entity.City;
import com.codecool.solarwatch.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final GeocodingService geocodingService;

    public CityService(CityRepository cityRepository, GeocodingService geocodingService) {
        this.cityRepository = cityRepository;
        this.geocodingService = geocodingService;
    }

    public City getCityByName(String cityName) {
        Optional<City> existingCity = cityRepository.findByName(cityName);

        if (existingCity.isEmpty()) {
            GeoLocationReport report = geocodingService.getCoordinates(cityName);
            City city = new City();
            city.setName(cityName);
            city.setLatitude(report.lat());
            city.setLongitude(report.lon());
            city.setState(report.state());
            city.setCountry(report.country());
            cityRepository.save(city);
            return city;

        } else {
            return existingCity.get();
        }
    }
}
