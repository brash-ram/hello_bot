package com.kpd.kpd_bot.api.news;

import com.kpd.kpd_bot.entity.cache.News;
import com.kpd.kpd_bot.jpa.cache.NewsRepository;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class NewsService {
	
	private final NewsRepository newsRepository;
	private final NewsAPI newsAPI;
	
	public News getNews() {
		News News = this.getNews(DateGetter.getSqlDate());
		if (News == null) {
			News = this.getNewsFromApiAndSave();
		} else {
			if (DateGetter.getDifferanceDay(News.getDateUpdate()) > 0) {
				Long id = News.getId();
				News = this.getNewsFromApiAndSave();
				newsRepository.deleteById(id);
			}
		}
		return News;
	}


	private News getNews(Date dateUpdate) {
		News News = newsRepository.findByDateUpdateLessThanEqual(dateUpdate);
		Hibernate.initialize(News);
		return News;
	}

	private News getNewsFromApiAndSave() {
		return newsRepository.save(newsAPI.getNews().setDateUpdate(DateGetter.getSqlDate()));
	}
}
