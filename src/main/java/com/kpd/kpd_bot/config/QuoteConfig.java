package com.kpd.kpd_bot.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "quote")
@Data
@RequiredArgsConstructor
public class QuoteConfig {
	private String url;
	private String token;
}
