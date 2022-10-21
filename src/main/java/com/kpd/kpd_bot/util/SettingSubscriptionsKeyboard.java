package com.kpd.kpd_bot.util;


import com.kpd.kpd_bot.bot.MessageAdapter;
import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.statics.StringConst;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class SettingSubscriptionsKeyboard {
	private static final String yes = "✅";
	private static final String no = "❌";

	public static InlineKeyboardMarkup createInlineKeyboardSettingSubscription(Subscription subscription) {
		InlineKeyboardConstructor constructor = new InlineKeyboardConstructor();
		constructor.addInlineButtonInRow(StringConst.BACK, "backSubscription").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.WEATHER + " " + (subscription.getWeather() ? yes : no), "weather").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.QUOTE + " " + (subscription.getQuote() ? yes : no), "quote").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.FILM + " " + (subscription.getFilm() ? yes : no), "film").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.EXCHANGE_RATES + " " + (subscription.getExchangeRates() ? yes : no), "exchangeRates").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.NEWS + " " + (subscription.getNews() ? yes : no), "news").addNewInlineRow();

		if (subscription.getWeather()) {
			constructor.addInlineButtonInRow("Настроить город для прогноза погоды", "setUserCity");
		}

		if (subscription.getExchangeRates()) {
			constructor.addNewInlineRow().addInlineButtonInRow("Выбрать необходимые курсы валют", "setCurrencies");
		}

		return constructor.getInlineKeyboard();
	}

	public static InlineKeyboardMarkup createInlineKeyboardExchangeRatesSetting(ExchangeRatesSetting exchangeRatesSetting) {
		InlineKeyboardConstructor constructor = new InlineKeyboardConstructor();
		constructor.addInlineButtonInRow(StringConst.CHF_RUB + " " + (exchangeRatesSetting.getCHF_RUB() ? yes : no), "CHF/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.JPY_RUB + " " + (exchangeRatesSetting.getJPY_RUB() ? yes : no), "JPY/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.EUR_RUB + " " + (exchangeRatesSetting.getEUR_RUB()? yes : no), "EUR/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.GBP_RUB + " " + (exchangeRatesSetting.getGBP_RUB() ? yes : no), "GBP/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.USD_RUB + " " + (exchangeRatesSetting.getUSD_RUB() ? yes : no), "USD/RUB").addNewInlineRow();
		return constructor.getInlineKeyboard();
	}

}
