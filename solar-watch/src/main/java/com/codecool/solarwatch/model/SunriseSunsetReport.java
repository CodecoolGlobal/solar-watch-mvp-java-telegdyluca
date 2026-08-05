package com.codecool.solarwatch.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record SunriseSunsetReport(String city, LocalDate date, LocalTime sunrise, LocalTime sunset) {}
