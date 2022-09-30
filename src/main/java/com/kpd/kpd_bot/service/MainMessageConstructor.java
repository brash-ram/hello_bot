package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.quote.QuoteAdapter;
import com.kpd.kpd_bot.api.weather.WeatherAdapter;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.jpa.UserRepository;
import com.kpd.kpd_bot.statics.StringConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainMessageConstructor {
	private final Adapter weatherAdapter;
	private final Adapter quoteAdapter;
	private final UserRepository userRepository;

	public String getMessage(Long userId) {
		StringBuilder sb = new StringBuilder();
		sb.append(StringConst.helloMessage).append("\n");
		Subscription subscription = userRepository.getReferenceById(userId).getSubscription();
		if (subscription.isQuote()) {
			sb.append(quoteAdapter.getTextFromMessageService()).append("\n");
		}
		if (subscription.isWeather()) {
			sb.append(weatherAdapter.getTextFromMessageService()).append("\n");
		}
		return sb.toString();
	}
}
