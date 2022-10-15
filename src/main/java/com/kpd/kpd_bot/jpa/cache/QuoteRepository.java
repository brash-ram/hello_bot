package com.kpd.kpd_bot.jpa.cache;

import com.kpd.kpd_bot.entity.cache.Film;
import com.kpd.kpd_bot.entity.cache.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
	Quote findByDateUpdateLessThanEqual(Date dateUpdate);
}