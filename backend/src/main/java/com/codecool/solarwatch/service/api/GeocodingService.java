package com.codecool.solarwatch.service.api;

import com.codecool.solarwatch.exception.CityNotFoundException;
import com.codecool.solarwatch.model.dto.GeoLocationReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {

    @Value("${OPENWEATHER_API_KEY}")
    private String apiKey;

    @Value("${codecool.app.geourl}")
    private String geoUrl;

    private final RestTemplate restTemplate;

    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GeoLocationReport getCoordinates(String city) {
        String url = String.format(geoUrl, city, apiKey);

        GeoLocationReport[] response = restTemplate.getForObject(url, GeoLocationReport[].class);

        if (response == null || response.length == 0) {
            throw new CityNotFoundException();
        }

        return response[0];
    }
}