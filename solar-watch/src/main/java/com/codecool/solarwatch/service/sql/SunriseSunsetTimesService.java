package com.codecool.solarwatch.service.sql;

import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimeEntity;
import com.codecool.solarwatch.model.payload.SunriseSunsetRequest;
import com.codecool.solarwatch.repository.CityRepository;
import com.codecool.solarwatch.repository.SunriseSunsetTimesRepository;
import com.codecool.solarwatch.service.api.SunriseSunsetService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class SunriseSunsetTimesService {

    private final SunriseSunsetTimesRepository sunriseSunsetTimesRepository;
    private final SunriseSunsetService sunriseSunsetService;
    private final CityRepository cityRepository;

    public SunriseSunsetTimesService(SunriseSunsetTimesRepository sunriseSunsetTimesRepository, SunriseSunsetService sunriseSunsetService, CityRepository cityRepository) {
        this.sunriseSunsetTimesRepository = sunriseSunsetTimesRepository;
        this.sunriseSunsetService = sunriseSunsetService;
        this.cityRepository = cityRepository;
    }

    public SunriseSunsetTimeEntity getSunriseSunsetByCityAndDate(CityEntity city, LocalDate date) {
        Optional<SunriseSunsetTimeEntity> existingSunriseSunsetTimes = sunriseSunsetTimesRepository.findByCityAndDate(city, date);

        if (existingSunriseSunsetTimes.isEmpty()) {
            SunriseSunsetReport report = sunriseSunsetService.getSunriseSunsetReport(city.getLatitude(), city.getLongitude(), date, city.getName());
            SunriseSunsetTimeEntity sunriseSunsetTimes = new SunriseSunsetTimeEntity();
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

    public void createSunriseSunsetTime(SunriseSunsetRequest request) {
        SunriseSunsetTimeEntity entity = new SunriseSunsetTimeEntity();
        CityEntity cityEntity = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new IllegalArgumentException(format("City with id: %s not found", request.getCityId())));

        entity.setCity(cityEntity);
        entity.setDate(request.getDate());
        entity.setSunrise(request.getSunrise());
        entity.setSunset(request.getSunset());
        sunriseSunsetTimesRepository.save(entity);
    }

    public void editSunriseSunsetTime(Long id, SunriseSunsetRequest request) {
        SunriseSunsetTimeEntity entity = sunriseSunsetTimesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("SunriseSunsetTime with city id: %s not found", request.getCityId())));

        CityEntity cityEntity = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new IllegalArgumentException(format("City with id: %s not found", request.getCityId())));

        entity.setCity(cityEntity);
        entity.setDate(request.getDate());
        entity.setSunrise(request.getSunrise());
        entity.setSunset(request.getSunset());
        sunriseSunsetTimesRepository.save(entity);
    }

    public void deleteSunriseSunsetTime(Long id) {
        SunriseSunsetTimeEntity entity = sunriseSunsetTimesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("SunriseSunsetTime with id: %s not found", id)));
        sunriseSunsetTimesRepository.delete(entity);
    }
}
