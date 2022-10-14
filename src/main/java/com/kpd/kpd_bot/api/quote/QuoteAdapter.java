package com.kpd.kpd_bot.api.quote;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QuoteAdapter {
    public static final String QUOTE_URL = "https://time365.info/aforizmi/drugoye/random.php?";
    public static final String ERROR_MESSAGE = "Цитаты сегодня нет.";
    public static final String DAY_QUOTE = "\n*Цитата дня*\n";
    public static final String CLASS_NAME = "mg-b-5 tx-inverse";
    public static final String TAG = "cite";

    private Document getQuotePage() {
        Document quotePage;
        try {
            quotePage = Jsoup.connect(QUOTE_URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return quotePage;
    }

    public String getTextFromQuotePage() {

        Document quotePage = this.getQuotePage();

        if (quotePage == null) {
            return ERROR_MESSAGE;
        }

        Quote quote = new Quote();
        quote.setQuote(quotePage.getElementsByClass(CLASS_NAME).text());
        quote.setAuthor(quotePage.getElementsByTag(TAG).text());

        return new StringBuilder()
                .append(DAY_QUOTE)
                .append(quote.getQuote())
                .append("\n_")
                .append(quote.getAuthor())
                .append("_")
                .toString();
    }

}
