package com.kpd.kpd_bot.api.quote;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.quote.model.BaseQuoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteAdapter implements Adapter {
	public static final String DAY_QUOTE = "\n*Цитата дня*\n";
	private final QuoteAPI quoteAPI;
	private final String ERROR_MESSAGE = "Сегодня без цитат)";

	@Override
	public String getTextFromMessageService(String... args) {
		BaseQuoteResponseDTO responseDTO;
		try {
			responseDTO = quoteAPI.getQuote();
		} catch (RuntimeException ex) {
			return ERROR_MESSAGE;
		}

		return this.formatFromObjectToText(responseDTO);
	}

	private String formatFromObjectToText(BaseQuoteResponseDTO dto) {
		if (dto == null) return ERROR_MESSAGE;
		StringBuilder result = new StringBuilder()
				.append(DAY_QUOTE)
				.append(dto.getQuote());

		if (dto.getAuthor() != null) {
			result.append("\n**")
					.append(dto.getAuthor())
					.append("**");
		}

		return result.toString();
	}
}
//	@EventListener(ApplicationReadyEvent.class)
//	void test(){
//		System.out.println(this.getTextFromMessageService());
//	}

