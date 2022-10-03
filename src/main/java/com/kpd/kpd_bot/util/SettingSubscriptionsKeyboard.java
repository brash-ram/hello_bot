package com.kpd.kpd_bot.util;


import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.statics.StringConst;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class SettingSubscriptionsKeyboard {

//			StringConst.weather,
//			StringConst.quote,
//			StringConst.film,
//			StringConst.exchangeRates,
//			StringConst.news

	private static final String yes = "✅";
	private static final String no = "❌";

	public static InlineKeyboardMarkup createInlineKeyboardSettingSubscription(Subscription subscription) {
		InlineKeyboardConstructor constructor = new InlineKeyboardConstructor();
		constructor.addInlineButtonInRow(StringConst.weather + " " + (subscription.getWeather() ? yes : no), "weather").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.quote + " " + (subscription.getQuote() ? yes : no), "quote").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.film + " " + (subscription.getFilm() ? yes : no), "film").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.exchangeRates + " " + (subscription.getExchangeRates() ? yes : no), "exchangeRates").addNewInlineRow();
		constructor.addInlineButtonInRow(StringConst.news + " " + (subscription.getNews() ? yes : no), "news").addNewInlineRow();
		return constructor.getInlineKeyboard();
	}
}
