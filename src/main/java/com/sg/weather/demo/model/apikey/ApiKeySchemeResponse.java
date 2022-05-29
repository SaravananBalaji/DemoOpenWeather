package com.sg.weather.demo.model.apikey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiKeySchemeResponse {
    public Integer id;
    public String apiKey;
    public String apiName;
    private Long version;
    private Integer count;
    public String createdBy;
    public LocalDateTime createdDate;
    public String modifiedBy;
    public LocalDateTime modifiedDate;
}
