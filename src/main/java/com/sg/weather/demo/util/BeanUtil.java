package com.sg.weather.demo.util;

import com.sg.weather.demo.entity.ApiKey;
import com.sg.weather.demo.model.apikey.ApiKeySchemeResponse;

public class BeanUtil {
    public static ApiKeySchemeResponse toApiSchemeResponse(ApiKey apiKey) {
        return ApiKeySchemeResponse.builder()
                .id(apiKey.getId())
                .apiKey(apiKey.getApiKey())
                .apiName(apiKey.getApiName())
                .count(apiKey.getCount())
                .version(apiKey.getVersion())
                .createdBy(apiKey.getCreatedBy())
                .createdDate(apiKey.getCreatedDate())
                .modifiedBy(apiKey.getModifiedBy())
                .modifiedDate(apiKey.getModifiedDate())
                .build();
    }
}
