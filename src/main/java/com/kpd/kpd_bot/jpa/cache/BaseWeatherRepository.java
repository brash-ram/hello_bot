package com.kpd.kpd_bot.jpa.cache;

import com.kpd.kpd_bot.entity.cache.ExchangeRate;
import com.kpd.kpd_bot.entity.cache.weather.BaseWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Timestamp;

public interface BaseWeatherRepository extends JpaRepository<BaseWeather, Long> {
	BaseWeather findByTimeUpdateLessThanEqual(Timestamp dateUpdate);
}