package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.statics.StringConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainMessageConstructor {
	private final Adapter weatherAdapter;
	private final Adapter quoteAdapter;
	private final SubscriptionService subscriptionService;

	public String getMessage(Long userId) {
		StringBuilder sb = new StringBuilder();

		sb.append(StringConst.HELLO_MESSAGE).append("\n");
		Subscription subscription = subscriptionService.getSubscriptionByUserId(userId);

		if (subscription.getQuote()) {
			sb.append(quoteAdapter.getTextFromMessageService()).append("\n");
		}

		if (subscription.getWeather()) {
			sb.append(weatherAdapter.getTextFromMessageService()).append("\n");
		}
		return sb.toString();
	}
}
