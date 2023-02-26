package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.entity.*;
import com.kpd.kpd_bot.myenum.UserStateEnum;
import com.kpd.kpd_bot.service.*;
import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import com.kpd.kpd_bot.utils.InlineKeyboardConstructor;
import com.kpd.kpd_bot.utils.SettingKeyboard;
import com.kpd.kpd_bot.utils.TimeSendInlineKeyboardHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InlineKeyboardHandler {
	private final SubscriptionService subscriptionService;
	private final ExchangeRatesSettingService exchangeRatesSettingService;
	private final UserService userService;
	private final SettingService settingService;
	private final UserStateService userStateService;
	private final List<String> listSubscriptions = new ArrayList<String>(Arrays.asList("weather", "quote", "film", "exchangeRates", "news"));
	private final List<String> listCurrencies = new ArrayList<String>(Arrays.asList("CHF/RUB", "JPY/RUB", "EUR/RUB", "CNY/RUB", "USD/RUB", "GBP/RUB"));

	public void handleMessage(Update update, Bot bot) throws TelegramApiException {
		String callData = update.getCallbackQuery()
				.getData();

		int messageId = update.getCallbackQuery()
				.getMessage()
				.getMessageId();

		long chatId = update.getCallbackQuery().
				getMessage()
				.getChatId();

		String messageText = update.getCallbackQuery()
				.getMessage()
				.getText();

		Long userId = update.getCallbackQuery()
				.getFrom()
				.getId();

		UserInfo userInfo = userService.findById(userId);
		MessageAdapter newMessage = null;
		DeleteMessage deleteMessage = null;
		EditMessageText editMessage = this.editMessage(chatId, messageId, messageText, update.getCallbackQuery()
				.getMessage()
				.getReplyMarkup());

		switch (callData) {
			case "setSendMessage" -> {
				UserSetting userSetting = userInfo.getUserSetting();
				settingService.saveSetting(userSetting.setSendMainMessage(!userSetting.getSendMainMessage()));
				editMessage.setReplyMarkup(SettingKeyboard.createBasicSettingKeyboard(userSetting));
			}
			case "backSetting" -> {
				editMessage = this.editMessage(chatId, messageId, StringConst.SETTINGS_MESSAGE,
						SettingKeyboard.createBasicSettingKeyboard(userInfo.getUserSetting()));
				this.clearUserState(chatId);
			}

			case "backSubscription", "setMessageInfoParameters" -> {
				editMessage = this.editMessage(chatId, messageId, StringConst.NEWS_PARAMETERS_MESSAGE,
						SettingKeyboard.createInlineKeyboardSettingSubscription(userInfo.getSubscription()));
				this.clearUserState(chatId);
			}

			case "setTimeSend" -> {
				settingService.saveSetting(userInfo.getUserSetting().setTimeSend(messageText));
				editMessage = this.editMessage(chatId, messageId, StringConst.SETTINGS_MESSAGE,
						SettingKeyboard.createBasicSettingKeyboard(userInfo.getUserSetting()));
				newMessage = new MessageAdapter()
						.setChatId(chatId)
						.setText(StringConst.SUCCESSFULLY_SET_TIME_SEND);
			}

			case "setSendingMessageTime" -> {
				String currentTimeSend = userInfo.getUserSetting()
						.getTimeSend();
				editMessage = this.editMessage(chatId, messageId, currentTimeSend,
						Buttons.getSetTimeKeyboard());
			}

			case "setUserForm" -> {
				userStateService.saveUserState(userId, UserStateEnum.WAIT_NAME);
				editMessage = this.editMessage(chatId, messageId, StringConst.INPUT_NAME_FOR_USER,
						new InlineKeyboardConstructor().addInlineButtonInRow(StringConst.BACK, "backSetting")
								.getInlineKeyboard());
			}

			case "setUserCity" -> {
				editMessage = this.editMessage(chatId, messageId, StringConst.INPUT_CITY_FOR_USER,
						new InlineKeyboardConstructor().addInlineButtonInRow(StringConst.BACK, "backSubscription")
						.getInlineKeyboard());
				if (!userStateService.existByUserId(userId)) {
					userStateService.saveUserState(userId, UserStateEnum.WAIT_CITY);
				}
			}

			case "setCurrencies" -> {
				editMessage.setText(StringConst.SET_CURRENCIES);
				this.handleExchangeRatesSetting(callData, editMessage, userInfo.getExchangeRatesSetting());
			}

			case "setNewsCategory" -> {
				editMessage.setText(StringConst.SET_NEWS_CATEGORY);
				this.handleNewsCategorySetting(callData, editMessage, userInfo.getUserSetting());
			}

			default -> {
				if (listSubscriptions.contains(callData)) {
					this.handleSettingSubscription(callData, editMessage, userInfo.getSubscription());
				} else if (StringConst.NEWS_CATEGORIES.containsKey(callData)) {
					this.handleNewsCategorySetting(callData, editMessage, userInfo.getUserSetting());
				} else if (listCurrencies.contains(callData)) {
					this.handleExchangeRatesSetting(callData, editMessage, userInfo.getExchangeRatesSetting());
				} else if (TimeSendInlineKeyboardHandler.listTimeButtons.contains(callData)) {
					editMessage.setText(TimeSendInlineKeyboardHandler.getNewTime(messageText, callData));
				} else {
					editMessage = null;
				}
			}
	}

		if (newMessage != null) {
			bot.execute(newMessage.getSendMessage());
		}

		if (editMessage != null) {
			bot.execute(editMessage);
		}

		if (deleteMessage != null) {
			bot.execute(deleteMessage);
		}

	}

	private EditMessageText handleSettingSubscription(String field, EditMessageText editMessage, Subscription subscription) {
		switch (field) {
			case "weather" -> subscription = subscription.setWeather(!subscription.getWeather());
			case "quote" -> subscription = subscription.setQuote(!subscription.getQuote());
			case "film" -> subscription = subscription.setFilm(!subscription.getFilm());
			case "exchangeRates" -> subscription = subscription.setExchangeRates(!subscription.getExchangeRates());
			case "news" -> subscription = subscription.setNews(!subscription.getNews());
		}

		subscriptionService.saveSubscription(subscription);
		editMessage.setReplyMarkup(SettingKeyboard.createInlineKeyboardSettingSubscription(subscription));
		return editMessage;
	}

	private EditMessageText handleExchangeRatesSetting(String field, EditMessageText editMessage, ExchangeRatesSetting exchangeRatesSetting) {
		switch (field) {
			case "CHF/RUB" -> exchangeRatesSetting = exchangeRatesSetting.setCHF_RUB(!exchangeRatesSetting.getCHF_RUB());
			case "JPY/RUB" -> exchangeRatesSetting = exchangeRatesSetting.setJPY_RUB(!exchangeRatesSetting.getJPY_RUB());
			case "EUR/RUB" -> exchangeRatesSetting = exchangeRatesSetting.setEUR_RUB(!exchangeRatesSetting.getEUR_RUB());
			case "CNY/RUB" -> exchangeRatesSetting = exchangeRatesSetting.setCNY_RUB(!exchangeRatesSetting.getCNY_RUB());
			case "USD/RUB" -> exchangeRatesSetting = exchangeRatesSetting.setUSD_RUB(!exchangeRatesSetting.getUSD_RUB());
			case "GBP/RUB" -> exchangeRatesSetting = exchangeRatesSetting.setGBP_RUB(!exchangeRatesSetting.getGBP_RUB());
		}

		exchangeRatesSettingService.saveExchangeRatesSetting(exchangeRatesSetting);
		editMessage.setReplyMarkup(SettingKeyboard.createInlineKeyboardExchangeRatesSetting(exchangeRatesSetting));
		return editMessage;
	}

	private EditMessageText handleNewsCategorySetting(String field, EditMessageText editMessage, UserSetting setting) {
		StringConst.NEWS_CATEGORIES.keySet().forEach(key -> {
			if (key.equals(field)) {
				setting.setNewsCategory(key);
			}
		});
		settingService.saveSetting(setting);
		editMessage.setReplyMarkup(SettingKeyboard.createInlineKeyboardNewsCategorySetting(setting));
		return editMessage;
	}


	private EditMessageText editMessage(long chatId, int messageId, String text, InlineKeyboardMarkup keyboardMarkup) {
		EditMessageText editMessage = new EditMessageText();
		editMessage.setChatId(chatId);
		editMessage.setMessageId(messageId);
		editMessage.setText(text);
		editMessage.setReplyMarkup(keyboardMarkup);
		return editMessage;
	}

	private DeleteMessage deleteMessage(long chatId, int messageId) {
		DeleteMessage deleteMessage = new DeleteMessage();
		deleteMessage.setChatId(chatId);
		deleteMessage.setMessageId(messageId);
		return deleteMessage;
	}

	private void clearUserState(Long userId) {
		UserState userState = userStateService.findUserState(userId);
		if (userState != null) {
			userStateService.deleteUserState(userId);
		}
	}
}
