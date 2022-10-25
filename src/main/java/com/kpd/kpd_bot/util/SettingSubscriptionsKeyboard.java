package com.kpd.kpd_bot.util;


import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.entity.UserSetting;
import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class SettingSubscriptionsKeyboard {
	private static final String yes = "✅";
	private static final String no = "❌";

	public static InlineKeyboardMarkup createInlineKeyboardSettingSubscription(Subscription subscription) {
		InlineKeyboardConstructor constructor = new InlineKeyboardConstructor();
		constructor.addInlineButtonInRow(StringConst.BACK, "backSetting").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.WEATHER + " " + (subscription.getWeather() ? yes : no), "weather").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.QUOTE + " " + (subscription.getQuote() ? yes : no), "quote").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.FILM + " " + (subscription.getFilm() ? yes : no), "film").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.EXCHANGE_RATES + " " + (subscription.getExchangeRates() ? yes : no), "exchangeRates").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.NEWS + " " + (subscription.getNews() ? yes : no), "news").addNewInlineRow();

		if (subscription.getWeather()) {
			constructor.addInlineButtonInRow("Настроить город для прогноза погоды", "setUserCity");
//			constructor.addInlineButtonInRow("Выбрать тип прогноза погоды", "setForecastType");

		}

		if (subscription.getExchangeRates()) {
			constructor.addNewInlineRow().addInlineButtonInRow("Выбрать необходимые курсы валют", "setCurrencies");
		}

		if (subscription.getNews()) {
			constructor.addNewInlineRow().addInlineButtonInRow("Выбрать категорию новостей", "setNewsCategory");
		}

		return constructor.getInlineKeyboard();
	}

	public static InlineKeyboardMarkup createInlineKeyboardExchangeRatesSetting(ExchangeRatesSetting exchangeRatesSetting) {
		InlineKeyboardConstructor constructor = new InlineKeyboardConstructor();
		constructor.addInlineButtonInRow(StringConst.BACK, "backSubscription").addNewInlineRow();
		constructor.addInlineButtonInRow("\uD83C\uDDFA\uD83C\uDDF8" + " " + StringConst.USD_RUB + " " + (exchangeRatesSetting.getUSD_RUB() ? yes : no), "USD/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow("\uD83C\uDDEA\uD83C\uDDFA" + " " + StringConst.EUR_RUB + " " + (exchangeRatesSetting.getEUR_RUB()? yes : no), "EUR/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow("\uD83C\uDDEC\uD83C\uDDE7" + " " + StringConst.GBP_RUB + " " + (exchangeRatesSetting.getGBP_RUB() ? yes : no), "GBP/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow("\uD83C\uDDE8\uD83C\uDDED" + " " + StringConst.CHF_RUB + " " + (exchangeRatesSetting.getCHF_RUB() ? yes : no), "CHF/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow("\uD83C\uDDE8\uD83C\uDDF3" + " " + StringConst.CNY_RUB + " " + (exchangeRatesSetting.getCNY_RUB() ? yes : no), "CNY/RUB").addNewInlineRow();
		constructor.addInlineButtonInRow("\uD83C\uDDEF\uD83C\uDDF5" + " " + StringConst.JPY_RUB + " " + (exchangeRatesSetting.getJPY_RUB() ? yes : no), "JPY/RUB").addNewInlineRow();
		return constructor.getInlineKeyboard();
	}

	public static InlineKeyboardMarkup createInlineKeyboardNewsCategorySetting(UserSetting setting) {
		InlineKeyboardConstructor constructor = new InlineKeyboardConstructor();
		constructor.addInlineButtonInRow(StringConst.BACK, "backSubscription").addNewInlineRow();
		StringConst.NEWS_CATEGORIES.forEach((key, value) -> {
			constructor.addInlineButtonInRow(value + " " + (setting.getNewsCategory().equals(key) ? yes : no), key).addNewInlineRow();
		});
		return constructor.getInlineKeyboard();
	}

//	public static InlineKeyboardMarkup createInlineKeyboardForecastTypeSetting(ExchangeRatesSetting exchangeRatesSetting) {
//		InlineKeyboardConstructor constructor = new InlineKeyboardConstructor();
//		constructor.addInlineButtonInRow(StringConst.BACK, "backSubscription").addNewInlineRow();
//		return constructor.getInlineKeyboard();
//	}
}
