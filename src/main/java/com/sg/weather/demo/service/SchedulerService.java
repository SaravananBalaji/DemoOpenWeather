package com.sg.weather.demo.service;

import com.sg.weather.demo.repository.ApiKeyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.sg.weather.demo.constant.AppConstants.API_LiMIT_HOUR;

@Service
@Slf4j
public class SchedulerService {
    private final ApiKeyRepository apiKeyRepository;

    public SchedulerService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Scheduled(cron = "0 0 * ? * *") //every hour
//    @Scheduled(cron = "0 */1 * ? * *") //every 1 min, for testing
    public void resetApiLimit() {
        LocalDateTime hourAgo = LocalDateTime.now().minusHours(API_LiMIT_HOUR);
        apiKeyRepository.updateApiKeyResetLimit(hourAgo);
    }
}
