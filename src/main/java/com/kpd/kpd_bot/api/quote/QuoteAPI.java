package com.kpd.kpd_bot.api.quote;

import com.kpd.kpd_bot.config.QuoteConfig;
import com.kpd.kpd_bot.entity.cache.Quote;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QuoteAPI {

	public static final String CLASS_NAME = "mg-b-5 tx-inverse";
	public static final String TAG = "cite";
	private final QuoteConfig quoteConfig;

	public Quote getQuote() {
		Document quotePage;

		try {
			quotePage = Jsoup.connect(quoteConfig.getUrl()).get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Quote quote = new Quote();
		quote.setQuote(quotePage.getElementsByClass(CLASS_NAME).text());
		quote.setAuthor(quotePage.getElementsByTag(TAG).text());
		return quote;
	}
}
