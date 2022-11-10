package com.kpd.kpd_bot.jpa.cache.weather;

import com.kpd.kpd_bot.entity.cache.weather.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}