package com.sg.weather.demo.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Weather {
    @Id
    @GeneratedValue
    public Integer id;
    @Column
    public Double lat;
    @Column
    public Double lon;
    @Column
    public String description;
    @Column
    public Integer timezone;
    @Column
    public Integer openWeatherId;
    @Column
    public String countryName;
    @Column(columnDefinition = "json")
    @Type(type = "json")
    public String jsonDetails;
    @Column(nullable = false)
    public String createdBy;
    @Column(nullable = false)
    public LocalDateTime createdDate;
    @Column
    public String modifiedBy;
    @Column
    public LocalDateTime modifiedDate;
}
