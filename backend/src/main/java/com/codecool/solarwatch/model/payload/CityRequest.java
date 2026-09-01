package com.codecool.solarwatch.model.payload;

import lombok.Data;

@Data
public class CityRequest {

    private String country;
    private double latitude;
    private double longitude;
    private String name;
    private String state;
}
