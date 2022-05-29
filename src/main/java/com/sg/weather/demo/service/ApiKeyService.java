package com.sg.weather.demo.service;

import com.sg.weather.demo.entity.ApiKey;
import com.sg.weather.demo.exception.NotFoundException;
import com.sg.weather.demo.model.apikey.ApiKeySchemeRequest;
import com.sg.weather.demo.model.apikey.ApiKeySchemeResponse;
import com.sg.weather.demo.repository.ApiKeyRepository;
import com.sg.weather.demo.util.BeanUtil;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sg.weather.demo.constant.AppConstants.*;

@Service
@Slf4j
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public List<ApiKeySchemeResponse> getAll() {
        List<ApiKey> apiKeyList = apiKeyRepository.findAll();

        return apiKeyList
                .stream()
                .map(BeanUtil::toApiSchemeResponse)
                .collect(Collectors.toList());
    }

    public ApiKeySchemeResponse get(Integer id) {
        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findById(id);
        if (apiKeyOptional.isEmpty()) {
            throw new NotFoundException(API_NOT_FOUND);
        }

        return BeanUtil.toApiSchemeResponse(apiKeyOptional.get());
    }

    public ApiKeySchemeResponse create(ApiKeySchemeRequest apiKeySchemeRequest) {
        ApiKey apiKey = ApiKey.builder()
                .apiKey(apiKeySchemeRequest.getApiKey())
                .apiName(apiKeySchemeRequest.getApiName())
                .count(0)
                .createdBy(SYSTEM)
                .createdDate(LocalDateTime.now())
                .modifiedBy(SYSTEM)
                .modifiedDate(LocalDateTime.now())
                .build();
        apiKey = apiKeyRepository.save(apiKey);

        return BeanUtil.toApiSchemeResponse(apiKey);
    }

    public ApiKeySchemeResponse update(ApiKeySchemeRequest apiKeySchemeRequest, Integer id) {
        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findById(id);
        if (apiKeyOptional.isEmpty()) {
            throw new NotFoundException(API_NOT_FOUND);
        }
        ApiKey apiKey = apiKeyOptional.get();
        apiKey.setApiName(apiKeySchemeRequest.getApiName());
        apiKey.setApiKey(apiKeySchemeRequest.getApiKey());
        apiKey.setModifiedDate(LocalDateTime.now());
        apiKey = apiKeyRepository.save(apiKey);

        return BeanUtil.toApiSchemeResponse(apiKey);
    }

    public void delete(Integer id) {
        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findById(id);
        if (apiKeyOptional.isEmpty()) {
            throw new NotFoundException(API_NOT_FOUND);
        }
        ApiKey apiKey = apiKeyOptional.get();
        apiKeyRepository.delete(apiKey);
    }

    @Synchronized
    public boolean isReachedRateLimit(ApiKey apiKey) {
        return apiKey.getCount() >= API_LiMIT_TIMES;
    }

    @Synchronized
    public Integer getCount(ApiKey apiKey) {
        return apiKey.getCount();
    }
}
