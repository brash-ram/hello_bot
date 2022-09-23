package com.kpd.kpd_bot.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather")
@Data
@RequiredArgsConstructor
public class WeatherConfig {
    private String url;
    private String token;
}
