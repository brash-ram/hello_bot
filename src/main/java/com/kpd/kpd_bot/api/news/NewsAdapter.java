package com.kpd.kpd_bot.api.news;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.entity.cache.News;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class NewsAdapter implements Adapter {

	private final NewsService newsService;
	private final String ERROR_MESSAGE = "К сожалению, в данный момент невозможно получить новости.";

	public static final String DAY_NEWS = "*Новость дня*\n";

	@Override
	public Future<String> getTextFromMessageService(String... args) {
		String result = ERROR_MESSAGE;
		try {
			result = this.formatFromObjectToText(newsService.getNews(args[0]));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		return new AsyncResult<>(result);
	}

	private String formatFromObjectToText(News dto) {
		return new StringBuilder()
				.append(DAY_NEWS)
				.append(dto.getDescription())
				.append("\n[Ссылка на официальный источник]("+dto.getLink()+")")
				.toString();
	}
}
