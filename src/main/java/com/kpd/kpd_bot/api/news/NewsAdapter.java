package com.kpd.kpd_bot.api.news;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.news.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsAdapter implements Adapter {

	private final NewsAPI newsAPI;

	public static final String DAY_NEWS = "\n*Новость дня из мира технологий*\n";

	@Override
	public String getTextFromMessageService(String... args) {
		News responseDTO = newsAPI.getNews();
		return this.formatFromObjectToText(responseDTO);
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
