package com.kpd.kpd_bot.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "news")
@Data
@RequiredArgsConstructor
public class NewsConfig {
    private String url;
    private String token;
}