package com.sg.weather.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {
    public static String toJson(Object object) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Exception white converting object to json {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
