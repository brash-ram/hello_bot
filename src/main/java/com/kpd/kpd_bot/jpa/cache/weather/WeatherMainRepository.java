package com.kpd.kpd_bot.jpa.cache.weather;

import com.kpd.kpd_bot.entity.cache.weather.WeatherMain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherMainRepository extends JpaRepository<WeatherMain, Long> {
}