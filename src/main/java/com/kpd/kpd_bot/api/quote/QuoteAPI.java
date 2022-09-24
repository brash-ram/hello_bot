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

	private String getUri() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/{mode}")
				.buildAndExpand("today");
		return uriComponents.toUriString();
	}

	public BaseQuoteResponseDTO getQuote() {
		Object responseApi =  webService.<Object[]>makeRequest(quoteConfig.getUrl(), this.getUri(), Object[].class)[0];
		return mapper.convertValue(responseApi, BaseQuoteResponseDTO.class);
	}
}
