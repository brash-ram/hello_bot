package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.film.FilmAdapter;
import com.kpd.kpd_bot.api.weather.WeatherAdapter;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.entity.UserInfo;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainMessageConstructor {
	private final WeatherAdapter weatherAdapter;
	private final Adapter quoteAdapter;

	private final FilmAdapter filmAdapter;
	private final UserService userService;

	public String getMessage(Long userId) {
		StringBuilder sb = new StringBuilder();

		UserInfo userInfo = userService.findById(userId);

		sb.append(DateGetter.getMessageWithTime()).append(userInfo.getName()).append("!\n");
		Subscription subscription = userInfo.getSubscription();

		if (subscription.getQuote()) {
			sb.append(quoteAdapter.getTextFromMessageService()).append("\n");
		}

		if (subscription.getWeather()) {
			sb.append(weatherAdapter.getTextFromMessageService(userInfo.getUserSetting().getCity())).append("\n");
		}

		if (subscription.getFilm()) {
			sb.append(filmAdapter.getTextFromMessageService()).append("\n");
		}

		return sb.toString();
	}
}
