package com.codecool.solarwatch.service.api;

import com.codecool.solarwatch.model.dto.SunriseSunsetApiResponse;
import com.codecool.solarwatch.model.dto.SunriseSunsetReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Service
public class SunriseSunsetService {

    private final RestTemplate restTemplate;

    public SunriseSunsetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SunriseSunsetReport getSunriseSunsetReport(double lat, double lon, LocalDate date, String city) {

        String url = String.format("https://api.sunrise-sunset.org/v2?lat=%s&lng=%s&date=%s", lat, lon, date);

        SunriseSunsetApiResponse response = restTemplate.getForObject(url, SunriseSunsetApiResponse.class);

        LocalTime sunrise = OffsetDateTime.parse(response.sunrise()).toLocalTime();
        LocalTime sunset = OffsetDateTime.parse(response.sunset()).toLocalTime();

        return new SunriseSunsetReport(city, date, sunrise, sunset);
    }
}
