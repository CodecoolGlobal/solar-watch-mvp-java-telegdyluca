package com.codecool.solarwatch.integration.controller;

import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimeEntity;
import com.codecool.solarwatch.repository.CityRepository;
import com.codecool.solarwatch.repository.SunriseSunsetTimesRepository;
import com.codecool.solarwatch.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SunriseSunsetControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private SunriseSunsetTimesRepository sunriseSunsetRepository;

    @Autowired
    private UserRepository userRepository;

    private static MockWebServer mockWebServer;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        mockWebServer = new MockWebServer();
        registry.add("codecool.app.geourl", () -> mockWebServer.url("/geo/1.0/direct?q=%s&appid=%s").toString());
        registry.add("codecool.app.sunriseurl", () -> mockWebServer.url("/v2?lat=%s&lng=%s&date=%s").toString());
    }

    @AfterAll
    static void stopMockServer() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @Transactional
    void getSunriseSunset_cityIsNotInDb() throws Exception {
        String geoJson = "[{\"lat\":47.4979,\"lon\":19.0402,\"name\":\"Kulcs\",\"country\":\"HU\"}]";

        String sunriseJson = "{\"sunrise\":\"2026-09-02T04:00:00+00:00\",\"sunset\":\"2026-09-02T17:20:00+00:00\"}";

        mockWebServer.enqueue(new MockResponse()
                .setBody(geoJson)
                .addHeader("Content-Type", "application/json"));

        mockWebServer.enqueue(new MockResponse()
                .setBody(sunriseJson)
                .addHeader("Content-Type", "application/json"));

        String token = getJwtToken();

        mockMvc.perform(get("/sunrise-sunset")
                        .param("city", "Kulcs")
                        .param("date", "2026-09-02")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Kulcs"))
                .andExpect(jsonPath("$.date").value("2026-09-02"))
                .andExpect(jsonPath("$.sunrise").value("04:00:00"))
                .andExpect((jsonPath("$.sunset").value("17:20:00")));
    }

    @Test
    @Transactional
    void getSunriseSunset_cityAlreadyInDB() throws Exception {
        CityEntity budapest = new CityEntity();
        budapest.setName("Budapest");
        budapest.setLatitude(47.4979);
        budapest.setLongitude(19.0402);
        budapest.setState("Budapest");
        budapest.setCountry("HU");
        cityRepository.save(budapest);

        SunriseSunsetTimeEntity times = new SunriseSunsetTimeEntity();
        times.setCity(budapest);
        times.setDate(LocalDate.of(2026, 8, 20));
        times.setSunrise(LocalTime.of(6, 0));
        times.setSunset(LocalTime.of(20, 0));
        sunriseSunsetRepository.save(times);

        String token = getJwtToken();

        mockMvc.perform(get("/sunrise-sunset")
                        .param("city", "Budapest")
                        .param("date", "2026-08-20")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Budapest"));
    }

    private String getJwtToken() throws Exception {
        userRepository.deleteByUsername("TestName");
        String body = "{\"username\": \"TestName\", \"password\": \"test_password\"}";

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();

        MvcResult result = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody).get("jwt").asText();
    }
}