package com.codecool.solarwatch.repository;

import com.codecool.solarwatch.model.entity.CityEntity;
import com.codecool.solarwatch.model.entity.SunriseSunsetTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SunriseSunsetTimesRepository extends JpaRepository<SunriseSunsetTimeEntity, Long> {

    Optional<SunriseSunsetTimeEntity> findByCityAndDate(CityEntity city, LocalDate date);

    void deleteByCityId(Long cityId);
}
