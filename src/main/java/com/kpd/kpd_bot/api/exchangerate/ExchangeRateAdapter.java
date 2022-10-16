package com.kpd.kpd_bot.api.exchangerate;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.entity.cache.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class ExchangeRateAdapter implements Adapter {

    public final ExchangeRatesService exchangeRatesService;
    private static final String ERROR_MESSAGE = "К сожалению, в данный момент невозможно получить курс валют.";
    private static final String EXCHANGE_RATE = "\n*Курс валют*\n";

    @Override
    public Future<String> getTextFromMessageService(String... args) {
       String result = ERROR_MESSAGE;
		try {
			result = this.formatFromObjectToText(exchangeRatesService.getExchangeRates());
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		return new AsyncResult<>(result);
    }

    private String formatFromObjectToText(ExchangeRate dto) {
        if (dto == null) {
            return ERROR_MESSAGE;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(EXCHANGE_RATE);
        dto.getRates().forEach((key, value) -> {
            sb.append(dto.getBase())
                    .append("/")
                    .append(key)
                    .append(": ")
                    .append(String.format("%.2f", 1 / value))
                    .append("\n");
        });
        return sb.toString();
    }
}
