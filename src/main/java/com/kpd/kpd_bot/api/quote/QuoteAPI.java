package com.kpd.kpd_bot.api.quote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpd.kpd_bot.service.WebService;
import com.kpd.kpd_bot.config.QuoteConfig;
import com.kpd.kpd_bot.api.quote.model.BaseQuoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class QuoteAPI {
	private final QuoteConfig quoteConfig;
	private final WebService webService;
	private final ObjectMapper mapper;

	private String getUrl() {
		return UriComponentsBuilder.newInstance()
				.scheme("https").host(quoteConfig.getUrl()).path("/{mode}")
				.buildAndExpand("today").toUriString();
	}

	public BaseQuoteResponseDTO getQuote() {
		Object responseApi =  webService.<Object[]>makeRequest(this.getUrl(), Object[].class)[0];
		return mapper.convertValue(responseApi, BaseQuoteResponseDTO.class);
	}
}
