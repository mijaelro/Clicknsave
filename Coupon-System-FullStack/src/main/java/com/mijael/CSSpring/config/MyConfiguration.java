package com.mijael.CSSpring.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mijael.CSSpring.security.Information;

@Configuration
public class MyConfiguration {

    @Bean
    public Map<String, Information> map() {
        return new HashMap<>();
    }
}
