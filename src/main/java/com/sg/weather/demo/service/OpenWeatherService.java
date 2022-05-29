package com.sg.weather.demo.service;

import com.sg.weather.demo.client.OpenWeatherClient;
import com.sg.weather.demo.entity.ApiKey;
import com.sg.weather.demo.exception.BadRequestException;
import com.sg.weather.demo.exception.GenericException;
import com.sg.weather.demo.exception.NotFoundException;
import com.sg.weather.demo.model.response.OpenWeatherResponse;
import com.sg.weather.demo.model.response.WeatherResponse;
import com.sg.weather.demo.repository.ApiKeyRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static com.sg.weather.demo.constant.AppConstants.*;

@Service
@Slf4j
public class OpenWeatherService {

    private final OpenWeatherClient openWeatherClient;
    private final ApiKeyRepository apiKeyRepository;
    private final ApiKeyService apiKeyService;
    private final WeatherService weatherService;

    public OpenWeatherService(OpenWeatherClient openWeatherClient, ApiKeyRepository apiKeyRepository, ApiKeyService apiKeyService, WeatherService weatherService) {
        this.openWeatherClient = openWeatherClient;
        this.apiKeyRepository = apiKeyRepository;
        this.apiKeyService = apiKeyService;
        this.weatherService = weatherService;
    }

    public WeatherResponse getWeatherReport(String countryName, String city, String apiName) {
        validateRequiredFields(countryName, apiName);

        var apiKeyOptional = apiKeyRepository.findByApiName(apiName);
        if (apiKeyOptional.isEmpty()) {
            throw new NotFoundException(API_NOT_FOUND);
        }
        ApiKey apiKey = apiKeyOptional.get();
        boolean isReachedRateLimit = apiKeyService.isReachedRateLimit(apiKey);
        if (isReachedRateLimit) {
            throw new BadRequestException(API_KEY_HOURLY_LIMIT_EXCEEDED);
        }

        OpenWeatherResponse openWeatherResponse;
        String description = null;
        try {
            openWeatherResponse = openWeatherClient.getWeatherReport(
                    countryName
                    , city
                    , apiKey.getApiKey());
            log.info("Open Weather response: {}", openWeatherResponse);

            //consider how to update count, from 200 status or any response status
            updateApiCount(apiKeyOptional.get());

            //save response to local db
            CompletableFuture.runAsync(() -> {
                weatherService.save(openWeatherResponse);
            }).exceptionally(ex -> {
                log.error("Exception while saving response to local db, exception: {}", ex.getMessage());
                ex.printStackTrace();
                return null;
            });

            if (openWeatherResponse.getWeather() != null
                    && !openWeatherResponse.getWeather().isEmpty()) {
                description = openWeatherResponse.getWeather().get(0).getDescription();
            }

        } catch (Exception e) {
            log.error("error while getting weather report from open weather", e);
            throw new GenericException(SYSTEM_ERROR);
        }

        return WeatherResponse.builder().description(description).build();
    }

    public ApiKey updateApiCount(ApiKey apiKey) {
        Integer count = apiKeyService.getCount(apiKey) + 1;
        apiKey.setCount(count);
        apiKey.setModifiedBy(SYSTEM);
        apiKey.setModifiedDate(LocalDateTime.now());
        return apiKeyRepository.save(apiKey);
    }

    public void validateRequiredFields(String countryName, String apiName) {
        if (StringUtils.isEmpty(countryName)) {
            throw new BadRequestException(COUNTRY_NAME_REQUIRED);
        }
        if (StringUtils.isEmpty(apiName)) {
            throw new BadRequestException(API_NAME_REQUIRED);
        }
    }
}
