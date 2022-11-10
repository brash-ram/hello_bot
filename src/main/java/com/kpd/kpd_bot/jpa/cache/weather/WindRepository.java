package com.kpd.kpd_bot.jpa.cache.weather;

import com.kpd.kpd_bot.entity.cache.weather.Wind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindRepository extends JpaRepository<Wind, Long> {
}