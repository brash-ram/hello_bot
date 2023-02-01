package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.api.exchangerate.ExchangeRatesAdapter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class MainMessageConstructor {
	private final WeatherAdapter weatherAdapter;
	private final QuoteAdapter quoteAdapter;
	private final NewsAdapter newsAdapter;
	private final FilmAdapter filmAdapter;
	private final ExchangeRatesAdapter exchangeRateAdapter;
	private final UserService userService;

	public String getMessage(Long userId) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();

		UserInfo userInfo = userService.findById(userId);

		sb.append(DateGetter.getMessageWithTime()).append(userInfo.getName()).append("!\n");
		Subscription subscription = userInfo.getSubscription();
		List<Future<String>> futures = new ArrayList<>();

		if (subscription.getQuote()) {
			futures.add(quoteAdapter.getTextFromMessageService());
		}

		if (subscription.getWeather()) {
			futures.add(weatherAdapter.getTextFromMessageService(userInfo.getUserSetting().getCity(), String.valueOf(userId)));
		}
		if (subscription.getFilm()) {
			futures.add(filmAdapter.getTextFromMessageService());
		}

		if (subscription.getExchangeRates()) {
			futures.add(exchangeRateAdapter.getTextFromMessageService(userId.toString()));
		}

		if (subscription.getNews()) {
			futures.add(newsAdapter.getTextFromMessageService(userInfo.getUserSetting().getNewsCategory()));
		}
		long i = System.currentTimeMillis();
		boolean flag = true;
		while (flag) {
			flag = false;
			for (Future<String> stringFuture : futures) {
				if (!stringFuture.isDone()) {
					flag = true;
				}
			}
		}
		System.out.println(System.currentTimeMillis() - i);

		futures.forEach(stringFuture -> {
			try {
				sb.append(stringFuture.get()).append("\n");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});

		return sb.toString();
	}
}
