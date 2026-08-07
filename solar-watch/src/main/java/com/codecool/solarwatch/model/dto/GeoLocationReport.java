package com.codecool.solarwatch.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoLocationReport(double lat, double lon, String country, String state) {}
