package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRatesSettingRepository extends JpaRepository<ExchangeRatesSetting, Long> {
}