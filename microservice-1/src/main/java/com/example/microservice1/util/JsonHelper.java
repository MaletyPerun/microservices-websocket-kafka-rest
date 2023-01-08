package com.example.microservice1.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonHelper {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
//        https://howtodoinjava.com/jackson/java-8-date-time-type-not-supported-by-default/
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
    }

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("error with conversation into JSON = {}", e.getMessage());
            // TODO: 08.01.2023 обработка исключений
            throw new RuntimeException(e);
        }
    }
}