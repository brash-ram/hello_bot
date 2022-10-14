package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.api.film.FilmAdapter;
import com.kpd.kpd_bot.api.news.NewsAdapter;
import com.kpd.kpd_bot.api.weather.WeatherAdapter;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.entity.UserInfo;
import com.kpd.kpd_bot.api.quote.QuoteAdapter;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MainMessageConstructor {
	private final WeatherAdapter weatherAdapter;
	private final QuoteAdapter quoteAdapter;
	private final NewsAdapter newsAdapter;
	private final FilmAdapter filmAdapter;
	private final UserService userService;

	public String getMessage(Long userId) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();

		UserInfo userInfo = userService.findById(userId);

		sb.append(DateGetter.getMessageWithTime()).append(userInfo.getName()).append("!\n");
		Subscription subscription = userInfo.getSubscription();

		if (subscription.getQuote()) {
			sb.append(quoteAdapter.getTextFromQuotePage()).append("\n");
		}

		if (subscription.getWeather()) {
			sb.append(weatherAdapter.getTextFromMessageService(userInfo.getUserSetting().getCity())).append("\n");
		}

		if (subscription.getFilm()) {
			sb.append(filmAdapter.getTextFromMessageService()).append("\n");
		}

		if (subscription.getNews()) {
			sb.append(newsAdapter.getTextFromMessageService()).append("\n");
		}

		return sb.toString();
	}
}
