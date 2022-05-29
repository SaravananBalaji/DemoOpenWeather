package com.sg.weather.demo.controller;

import com.sg.weather.demo.model.response.WeatherResponse;
import com.sg.weather.demo.service.OpenWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sg.weather.demo.constant.AppConstants.API_GET_WEATHER;

@Slf4j
@Controller
public class WeatherController {
    private final OpenWeatherService openWeatherService;

    public WeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @Operation(summary = "Get weather report from open weather service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
            , @ApiResponse(responseCode = "404", description = "Not found")
            , @ApiResponse(responseCode = "400", description = "Bad request")
            , @ApiResponse(responseCode = "500", description = "System error")
    })
    @GetMapping(API_GET_WEATHER)
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam(value = "countryCode", required = false) String countryCode,
            @RequestParam(value = "apiName", required = false) @NonNull String apiName) {
        var weatherResponse = openWeatherService.getWeatherReport(countryName, countryCode, apiName);
        return ResponseEntity.ok(weatherResponse);
    }
}
