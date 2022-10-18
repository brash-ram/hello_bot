package com.kpd.kpd_bot.util;


import com.kpd.kpd_bot.bot.MessageAdapter;
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

//		if (subscription.getExchangeRates()) {
//			constructor.addInlineButtonInRow("Выбрать необходимые курсы валют")
//		}
		return constructor.getInlineKeyboard();
	}
}
