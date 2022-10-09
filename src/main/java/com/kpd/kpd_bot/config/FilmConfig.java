package com.kpd.kpd_bot.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "film")
@Data
@RequiredArgsConstructor
public class FilmConfig {
    private String url;
    private String token;
}
