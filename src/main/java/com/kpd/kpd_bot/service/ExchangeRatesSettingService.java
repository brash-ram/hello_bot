package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import com.kpd.kpd_bot.jpa.ExchangeRatesSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRatesSettingService {
	private final ExchangeRatesSettingRepository ratesSettingRepository;

	public ExchangeRatesSetting saveNewExchangeRatesSetting() {
		return ratesSettingRepository.save(new ExchangeRatesSetting(null, "usd", "rub", true, true, true, true, true, true));
	}
	public ExchangeRatesSetting saveExchangeRatesSetting(ExchangeRatesSetting ratesSetting) {
		return ratesSettingRepository.save(ratesSetting);
	}

}
