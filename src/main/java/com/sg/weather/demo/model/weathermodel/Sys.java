package com.sg.weather.demo.model.weathermodel;

import lombok.Data;

@Data
public class Sys {
    private Integer id;
    private Integer type;
    private String country;
    private Integer sunrise;
    private Integer sunset;
}
