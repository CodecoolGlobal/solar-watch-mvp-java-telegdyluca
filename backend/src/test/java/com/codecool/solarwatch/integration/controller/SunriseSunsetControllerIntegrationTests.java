package com.codecool.solarwatch.integration.controller;

import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.repository.CityRepository;
import com.codecool.solarwatch.repository.SunriseSunsetTimesRepository;
import com.codecool.solarwatch.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Test
    void getSunriseSunset_cityAlreadyInDB() throws Exception {
        String token = getJwtToken();

        mockMvc.perform(get("/sunrise-sunset")
                        .param("city", "Budapest")
                        .param("date", "2026-08-20")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Budapest"));
    }

    @Test
    @Transactional
    void getSunriseSunset_cityIsNotInDB() throws Exception {
        Optional<CityEntity> existingCity = cityRepository.findByName("Kulcs");
        if (!existingCity.isEmpty()) {
            Long id = existingCity.get().getId();
            sunriseSunsetRepository.deleteByCityId(id);
            cityRepository.deleteByName("Kulcs");
        }

        String token = getJwtToken();

        mockMvc.perform(get("/sunrise-sunset")
                        .param("city", "Kulcs")
                        .param("date", "2026-09-02")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Kulcs"));
    }

    private String getJwtToken() throws Exception {
        userRepository.deleteByUsername("TestName");
        String body = "{\"username\": \"TestName\", \"password\": \"test_password\"}";

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();

        MvcResult result = mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody).get("jwt").asText();
    }
}