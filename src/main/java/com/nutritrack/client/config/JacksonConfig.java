package com.nutritrack.client.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Register Java 8 Date/Time module
        mapper.registerModule(new JavaTimeModule());
        // Write dates as ISO8601 strings rather than timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // Force Jackson to use UTC as its default timezone
        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        return mapper;
    }
}