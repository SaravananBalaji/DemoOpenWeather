package com.sg.weather.demo.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponse {
    String description;
}
