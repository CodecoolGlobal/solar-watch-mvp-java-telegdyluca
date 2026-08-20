package com.codecool.solarwatch.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class SunriseSunsetTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CityEntity city;
    private LocalDate date;
    private LocalTime sunrise;
    private LocalTime sunset;

    public Long getId() {
        return id;
    }

    public CityEntity getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }
}
