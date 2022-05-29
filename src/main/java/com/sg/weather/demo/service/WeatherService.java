package com.sg.weather.demo.service;

import com.sg.weather.demo.constant.AppConstants;
import com.sg.weather.demo.entity.Weather;
import com.sg.weather.demo.model.response.OpenWeatherResponse;
import com.sg.weather.demo.repository.WeatherRepository;
import com.sg.weather.demo.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class WeatherService {
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void save(OpenWeatherResponse openWeatherResponse) {
        Weather weather = Weather.builder()
                .createdBy(AppConstants.SYSTEM)
                .createdDate(LocalDateTime.now())
                .timezone(openWeatherResponse.getTimezone())
                .openWeatherId(openWeatherResponse.getId())
                .countryName(openWeatherResponse.getName())
                .jsonDetails(JsonUtils.toJson(openWeatherResponse))
                .build();
        if (Objects.nonNull(openWeatherResponse.getCoord())) {
            weather.setLat(openWeatherResponse.getCoord().getLat());
            weather.setLon(openWeatherResponse.getCoord().getLon());
        }
        if (Objects.nonNull(openWeatherResponse.getWeather()) && !openWeatherResponse.getWeather().isEmpty()) {
            weather.setDescription(openWeatherResponse.getWeather().get(0).getDescription());
        }
        weatherRepository.save(weather);
    }
}
