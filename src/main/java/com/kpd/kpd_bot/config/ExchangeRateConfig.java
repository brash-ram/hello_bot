package com.kpd.kpd_bot.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "exchange-rate")
@Data
@RequiredArgsConstructor
public class ExchangeRateConfig {
    private String url;
    private String token;
}

