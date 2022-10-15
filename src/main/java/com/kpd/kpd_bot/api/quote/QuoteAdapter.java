package com.kpd.kpd_bot.api.quote;

import com.kpd.kpd_bot.entity.cache.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteAdapter {
    public static final String ERROR_MESSAGE = "Цитаты сегодня нет.";
    public static final String DAY_QUOTE = "\n*Цитата дня*\n";
    private final QuoteService quoteService;

    public String getTextFromQuotePage() {
        Quote quote;
        try {
            quote = quoteService.getQuote();
        } catch (RuntimeException ex) {
            return ERROR_MESSAGE;
        }

        return new StringBuilder()
                .append(DAY_QUOTE)
                .append(quote.getQuote())
                .append("\n_")
                .append(quote.getAuthor())
                .append("_")
                .toString();
    }

}
