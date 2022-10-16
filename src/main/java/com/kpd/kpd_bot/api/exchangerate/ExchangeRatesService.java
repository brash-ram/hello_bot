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
	private final ExchangeRateAPI exchangeRateAPI;
	private final ExchangeRateRepository exchangeRateRepository;

	public ExchangeRate getExchangeRates() {
		ExchangeRate ExchangeRate = this.getExchangeRates(DateGetter.getSqlDate());
		if (ExchangeRate == null) {
			ExchangeRate = this.getExchangeRatesFromApiAndSave();
		} else {
			if (DateGetter.getDifferanceDay(ExchangeRate.getDateUpdate()) > 0) {
				Long id = ExchangeRate.getId();
				ExchangeRate = this.getExchangeRatesFromApiAndSave();
				exchangeRateRepository.deleteById(id);
			}
		}
		return ExchangeRate;
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
