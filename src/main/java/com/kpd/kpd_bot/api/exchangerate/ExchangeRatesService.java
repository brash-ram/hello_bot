package com.kpd.kpd_bot.api.exchangerate;

import com.kpd.kpd_bot.entity.cache.ExchangeRate;
import com.kpd.kpd_bot.jpa.cache.ExchangeRateRepository;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {
	private final ExchangeRatesAPI exchangeRateAPI;
	private final ExchangeRateRepository exchangeRateRepository;

	public ExchangeRate getExchangeRates() {
		ExchangeRate exchangeRate = this.getExchangeRates(DateGetter.getSqlDate());
		if (exchangeRate == null) {
			exchangeRate = this.getExchangeRatesFromApiAndSave();
		} else {
			if (DateGetter.getDifferanceDay(exchangeRate.getDateUpdate()) > 0) {
				Long id = exchangeRate.getId();
				exchangeRate = this.getExchangeRatesFromApiAndSave();
				exchangeRateRepository.deleteById(id);
			}
		}
		return exchangeRate;
	}


	private ExchangeRate getExchangeRates(Date dateUpdate) {
		ExchangeRate ExchangeRate = exchangeRateRepository.findByDateUpdateLessThanEqual(dateUpdate);
		Hibernate.initialize(ExchangeRate);
		return ExchangeRate;
	}

	private ExchangeRate getExchangeRatesFromApiAndSave() {
		return exchangeRateRepository.save(exchangeRateAPI.getExchangeRate().setDateUpdate(DateGetter.getSqlDate()));
	}
}
