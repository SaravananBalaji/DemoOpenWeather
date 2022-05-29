package com.sg.weather.demo.model.response;

import com.sg.weather.demo.model.weathermodel.*;
import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherResponse {
    private Integer id;
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private Integer timezone;
    private String name;
    private Integer cod;
}
