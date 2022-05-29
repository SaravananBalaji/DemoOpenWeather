package com.sg.weather.demo.repository;

import com.sg.weather.demo.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
