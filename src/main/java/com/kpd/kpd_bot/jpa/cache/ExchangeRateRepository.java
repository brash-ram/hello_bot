package com.kpd.kpd_bot.jpa.cache;

import com.kpd.kpd_bot.entity.cache.ExchangeRate;
import com.kpd.kpd_bot.entity.cache.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
	ExchangeRate findByDateUpdateLessThanEqual(Date dateUpdate);
}