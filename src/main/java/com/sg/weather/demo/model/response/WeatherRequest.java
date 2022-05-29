package com.sg.weather.demo.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class WeatherRequest {
    String countryName;
    String countryCode;
    @NonNull
    String apiName;
}
