package com.kpd.kpd_bot.api.news;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.news.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class NewsAdapter implements Adapter {

	private final NewsAPI newsAPI;
	private final String ERROR_MESSAGE = "Новостей сегодня нет.";

	public static final String DAY_NEWS = "\n*Новость дня из мира технологий*\n";

	@Override
	public Future<String> getTextFromMessageService(String... args) {
		String result = ERROR_MESSAGE;
		try {
			result = this.formatFromObjectToText(newsAPI.getNews());
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		return new AsyncResult<>(result);
	}

	private String formatFromObjectToText(News dto) {
		return new StringBuilder()
				.append(DAY_NEWS)
				.append(dto.getTitle())
				.append("\nСсылка на официальный источник:\n")
				.append(dto.getLink())
				.toString();
	}
}
