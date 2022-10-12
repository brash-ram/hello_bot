package com.kpd.kpd_bot.api.quote;

import com.kpd.kpd_bot.service.WebService;
import com.kpd.kpd_bot.config.QuoteConfig;
import com.kpd.kpd_bot.api.quote.model.BaseQuoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class QuoteAPI {
	private final QuoteConfig quoteConfig;
	private final WebService webService;

	private String getUrl() {
		return UriComponentsBuilder.newInstance()
				.scheme("https").host(quoteConfig.getUrl()).path("?lang={lang}&curated=2")
				.buildAndExpand("ru").toUriString();
	}

	public BaseQuoteResponseDTO getQuote() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", String.format("Token %s", quoteConfig.getToken()));
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return webService.<BaseQuoteResponseDTO>makeGetRequest(this.getUrl(), BaseQuoteResponseDTO.class, headers);
	}
}
