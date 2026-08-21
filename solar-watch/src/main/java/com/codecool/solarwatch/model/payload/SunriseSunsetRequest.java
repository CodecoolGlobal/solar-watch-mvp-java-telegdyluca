package com.codecool.solarwatch.model.payload;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SunriseSunsetRequest {

    private Long cityId;
    private LocalDate date;
    private LocalTime sunrise;
    private LocalTime sunset;
}
