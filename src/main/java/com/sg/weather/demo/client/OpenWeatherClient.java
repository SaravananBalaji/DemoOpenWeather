package com.sg.weather.demo.client;

import com.sg.weather.demo.model.response.OpenWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.sg.weather.demo.constant.AppConstants.OPEN_WEATHER_URL;

@Service
@Slf4j
public class OpenWeatherClient {
    private final RestTemplate restTemplate;

    public OpenWeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenWeatherResponse getWeatherReport(String countryName, String city, String apiKey) {
        List<String> searchParamList = new ArrayList<>();

        if (StringUtils.isNotEmpty(city)) {
            searchParamList.add(city);
        }
        if (StringUtils.isNotEmpty(countryName)) {
            searchParamList.add(countryName);
        }
        String searchParam = String.join(",", searchParamList);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(OPEN_WEATHER_URL)
                .queryParam("q", searchParam)
                .queryParam("appid", apiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<OpenWeatherResponse> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                OpenWeatherResponse.class
        );
        return response.getBody();
    }
}
