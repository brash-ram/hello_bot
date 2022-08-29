package com.kpd.kpd_bot.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot")
@Data
@RequiredArgsConstructor
public class BotConfig {
		private String token;
		private String usernameBot;
}
