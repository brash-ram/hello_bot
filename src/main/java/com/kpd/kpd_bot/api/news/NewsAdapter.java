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
	private final String ERROR_MESSAGE = "\nК сожалению, в данный момент невозможно получить новости по этой категории.\n";
	private static final String DAY_NEWS = "\n*Новость дня*\n";
	private static final String LINK = "\n[Ссылка на официальный источник](";


	@Override
	public Future<String> getTextFromMessageService(String... args) {
		String result = ERROR_MESSAGE;

		try {
			result = this.formatFromObjectToText(newsService.getNews(args[0]));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return new AsyncResult<>(result);
	}

	private String formatFromObjectToText(News dto) {
		StringBuilder sb = new StringBuilder();
		sb.append(DAY_NEWS);

		if (dto.getDescription() != null) {
			sb.append(dto.getDescription());
		}

		sb.append(LINK + dto.getLink() + ")");
		return sb.toString();
	}
}
