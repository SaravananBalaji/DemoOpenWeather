package com.sg.weather.demo.repository;

import com.sg.weather.demo.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Integer> {
    Optional<ApiKey> findByApiName(String apiName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ApiKey apiKey set apiKey.count = 0 where apiKey.modifiedDate >= :timestamp")
    void updateApiKeyResetLimit(@Param("timestamp") LocalDateTime timestamp);
}
