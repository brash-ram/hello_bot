package com.kpd.kpd_bot.jpa.cache;

import com.kpd.kpd_bot.entity.cache.News;
import com.kpd.kpd_bot.entity.cache.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface NewsRepository extends JpaRepository<News, Long> {
	News findByDateUpdateLessThanEqual(Date dateUpdate);
}