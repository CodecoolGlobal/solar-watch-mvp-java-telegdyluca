package com.codecool.solarwatch.service.sql;

import com.codecool.solarwatch.model.dto.GeoLocationReport;
import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.model.payload.CityRequest;
import com.codecool.solarwatch.repository.CityRepository;
import com.codecool.solarwatch.service.api.GeocodingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final GeocodingService geocodingService;

    public CityService(CityRepository cityRepository, GeocodingService geocodingService) {
        this.cityRepository = cityRepository;
        this.geocodingService = geocodingService;
    }

    public CityEntity getCityByName(String cityName) {
        Optional<CityEntity> existingCity = cityRepository.findByName(cityName);

        if (existingCity.isEmpty()) {
            GeoLocationReport report = geocodingService.getCoordinates(cityName);
            CityEntity city = new CityEntity();
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

    public void createCity(CityRequest request) {
        CityEntity city = new CityEntity();
        city.setName(request.getName());
        city.setCountry(request.getCountry());
        city.setLatitude(request.getLatitude());
        city.setLongitude(request.getLongitude());
        city.setState(request.getState());
        cityRepository.save(city);
    }

    public void editCity(Long id, CityRequest request) {
        CityEntity city = cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("City %s not found", request.getName())));

        city.setName(request.getName());
        city.setCountry(request.getCountry());
        city.setLatitude(request.getLatitude());
        city.setLongitude(request.getLongitude());
        city.setState(request.getState());
        cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        CityEntity city = cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("City with id: %s not found", id)));
        cityRepository.delete(city);
    }
}
