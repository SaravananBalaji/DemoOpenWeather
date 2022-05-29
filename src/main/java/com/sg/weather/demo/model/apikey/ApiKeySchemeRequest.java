package com.sg.weather.demo.model.apikey;

import lombok.Data;
import lombok.NonNull;

@Data
public class ApiKeySchemeRequest {
    private String apiKey;
    private String apiName;
}
