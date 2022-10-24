package com.kpd.kpd_bot.api.exchangerate;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import com.kpd.kpd_bot.entity.cache.ExchangeRate;
import com.kpd.kpd_bot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class ExchangeRateAdapter implements Adapter {

    public final ExchangeRatesService exchangeRatesService;
	public final UserService userService;
    private static final String ERROR_MESSAGE = "К сожалению, в данный момент невозможно получить курс валют.";
    private static final String EXCHANGE_RATE = "\n*Курс валют*\n";

    @Override
    public Future<String> getTextFromMessageService(String... args) {
       String result = ERROR_MESSAGE;
		try {
			result = this.formatFromObjectToText(exchangeRatesService.getExchangeRates(), Long.parseLong(args[0]));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		return new AsyncResult<>(result);
    }

    private String formatFromObjectToText(ExchangeRate dto, Long userId) {
        if (dto == null) {
            return ERROR_MESSAGE;
        }
		ExchangeRatesSetting ratesSetting = userService.findById(userId).getExchangeRatesSetting();

		String base = dto.getBase();

        StringBuilder sb = new StringBuilder();
        sb.append(EXCHANGE_RATE);
        dto.getRates().forEach((key, value) -> {
			String valueString = String.format("%.2f", 1 / value);
            switch (key) {
				case "USD" -> {if (ratesSetting.getUSD_RUB()) this.addNewCurrencies(sb, key, base, valueString);}
				case "EUR" -> {if (ratesSetting.getEUR_RUB()) this.addNewCurrencies(sb, key, base, valueString);}
				case "CNY" -> {if (ratesSetting.getCNY_RUB()) this.addNewCurrencies(sb, key, base, valueString);}
				case "CHF" -> {if (ratesSetting.getCHF_RUB()) this.addNewCurrencies(sb, key, base, valueString);}
				case "JPY" -> {if (ratesSetting.getJPY_RUB()) this.addNewCurrencies(sb, key, base, valueString);}
			}
        });
        return sb.toString();
    }
	private void addNewCurrencies(StringBuilder sb, String key, String base, String value) {
		sb.append(key)
				.append("/")
				.append(base)
				.append(": ")
				.append(value)
				.append("\n");
	}
}
