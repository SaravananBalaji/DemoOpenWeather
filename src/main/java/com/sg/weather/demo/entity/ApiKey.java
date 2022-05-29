package com.sg.weather.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_key")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiKey {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(nullable = false)
    public String apiKey;
    @Column(nullable = false, unique = true)
    public String apiName;
    @Version
    private Long version;
    @Column
    private Integer count;
    @Column(nullable = false)
    public String createdBy;
    @Column(nullable = false)
    public LocalDateTime createdDate;
    @Column
    public String modifiedBy;
    @Column
    public LocalDateTime modifiedDate;
}
