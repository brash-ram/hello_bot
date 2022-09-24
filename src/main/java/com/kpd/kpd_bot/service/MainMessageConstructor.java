package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.quote.QuoteAdapter;
import com.kpd.kpd_bot.api.weather.WeatherAdapter;
import com.kpd.kpd_bot.statics.StringConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainMessageConstructor {
	private final Adapter weatherAdapter;
	private final Adapter quoteAdapter;

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(StringConst.helloMessage).append("\n")
				.append(quoteAdapter.getTextFromMessageService()).append("\n")
				.append(weatherAdapter.getTextFromMessageService()).append("\n");
		return sb.toString();
	}
}
