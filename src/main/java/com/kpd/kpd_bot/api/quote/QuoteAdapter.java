package com.kpd.kpd_bot.api.quote;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.entity.cache.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class QuoteAdapter implements Adapter {
    public static final String ERROR_MESSAGE = "Цитаты сегодня нет.";
    public static final String DAY_QUOTE = "\n*Цитата дня*\n";
    private final QuoteService quoteService;

    @Override
    public Future<String> getTextFromMessageService(String... args) {
        String result = ERROR_MESSAGE;
        try {
            result = this.formatFromObjectToText(quoteService.getQuote());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return new AsyncResult<>(result);
    }
    private String formatFromObjectToText(Quote quote) {
        return new StringBuilder()
                .append(DAY_QUOTE)
                .append(quote.getQuote())
                .append("\n_")
                .append(quote.getAuthor())
                .append("_")
                .toString();
    }

}
