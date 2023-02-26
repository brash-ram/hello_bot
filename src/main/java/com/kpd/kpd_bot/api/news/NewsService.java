package com.kpd.kpd_bot.api.news;

import com.kpd.kpd_bot.entity.cache.News;
import com.kpd.kpd_bot.jpa.cache.NewsRepository;
import com.kpd.kpd_bot.utils.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
	
	private final NewsRepository newsRepository;
	private final NewsAPI newsAPI;
	
	public News getNews(String category) {
		News news = this.getNews(DateGetter.getSqlDate(), category);
		if (news == null) {
			news = this.getNewsFromApiAndSave(category);
		} else {
			if (DateGetter.getDifferanceDay(news.getDateUpdate()) > 0) {
				Long id = news.getId();
				news = this.getNewsFromApiAndSave(category);
				newsRepository.deleteById(id);
			}
		}
		return news;
	}

	private News getNews(Date dateUpdate, String category) {
		List<News> news = newsRepository.findByDateUpdateLessThanEqual(dateUpdate);

		for (News item : news) {
			if (item.getCategories().contains(category)) {
				Hibernate.initialize(item);
				return item;
			}
		}

		return null;
	}

	private News getNewsFromApiAndSave(String category) {
		return newsRepository.save(newsAPI.getNews(category)
				.setDateUpdate(DateGetter.getSqlDate()));
	}
}
