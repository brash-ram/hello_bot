package com.kpd.kpd_bot.api.quote;

import com.kpd.kpd_bot.entity.cache.Quote;
import com.kpd.kpd_bot.jpa.cache.QuoteRepository;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class QuoteService {
	private final QuoteAPI quoteAPI;
	private final QuoteRepository quoteRepository;

	public Quote getQuote() {
		Quote quote = this.getQuote(DateGetter.getSqlDate());
		if (quote == null) {
			quote = this.getQuoteFromApiAndSave();
		} else {
			if (DateGetter.getDifferanceDay(quote.getDateUpdate()) > 0) {
				Long id = quote.getId();
				quote = this.getQuoteFromApiAndSave();
				quoteRepository.deleteById(id);
			}
		}
		return quote;
	}


	private Quote getQuote(Date dateUpdate) {
		Quote quote = quoteRepository.findByDateUpdateLessThanEqual(dateUpdate);
		Hibernate.initialize(quote);
		return quote;
	}

	private Quote getQuoteFromApiAndSave() {
		return quoteRepository.save(quoteAPI.getQuote().setDateUpdate(DateGetter.getSqlDate()));
	}
}
