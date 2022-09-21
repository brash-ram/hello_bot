package com.kpd.kpd_bot.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather")
@Data
@AllArgsConstructor
public class WeatherConfig {
    private String url;
}
