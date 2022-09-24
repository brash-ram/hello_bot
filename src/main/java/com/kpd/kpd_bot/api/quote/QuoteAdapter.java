package com.kpd.kpd_bot.api.quote;

import com.kpd.kpd_bot.api.quote.QuoteAPI;
import com.kpd.kpd_bot.api.quote.model.BaseQuoteResponseDTO;
import com.kpd.kpd_bot.api.Adapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteAdapter implements Adapter {
	private final QuoteAPI quoteAPI;

	@Override
	public String getTextFromMessageService() {
		BaseQuoteResponseDTO responseDTO = quoteAPI.getQuote();
		return this.formatFromObjectToText(responseDTO);
	}

	private String formatFromObjectToText(BaseQuoteResponseDTO dto) {
		StringBuilder sb = new StringBuilder();
		sb.append("Цитата дня:\n")
				.append(dto.getA()).append("\n")
				.append(dto.getQ());

		return sb.toString();
	}
}
