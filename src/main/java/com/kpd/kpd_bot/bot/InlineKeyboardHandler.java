package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.myenum.UserStateEnum;
import com.kpd.kpd_bot.service.SettingService;
import com.kpd.kpd_bot.service.SubscriptionService;
import com.kpd.kpd_bot.service.UserService;
import com.kpd.kpd_bot.service.UserStateService;
import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import com.kpd.kpd_bot.util.InlineKeyboardConstructor;
import com.kpd.kpd_bot.util.SettingSubscriptionsKeyboard;
import com.kpd.kpd_bot.util.TimeSendInlineKeyboardHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class InlineKeyboardHandler {
	private final TimeSendInlineKeyboardHandler timeSendInlineKeyboardHandler;
	private final SubscriptionService subscriptionService;
	private final UserService userService;
	private final SettingService settingService;
	private final UserStateService userStateService;

	public void handleMessage(Update update, Bot bot) throws TelegramApiException {
		String callData = update.getCallbackQuery().getData();
		int messageId = update.getCallbackQuery().getMessage().getMessageId();
		long chatId = update.getCallbackQuery().getMessage().getChatId();
		String messageText = update.getCallbackQuery().getMessage().getText();
		Long userId = update.getCallbackQuery().getFrom().getId();
		MessageAdapter newMessage = null;
		EditMessageText editMessage = this.editMessage(chatId, messageId, messageText, update.getCallbackQuery().getMessage().getReplyMarkup());

		switch (callData) {
			case "<<" ->
				editMessage = this.editMessage(chatId, messageId,
						timeSendInlineKeyboardHandler.subHour(messageText), update.getCallbackQuery().getMessage().getReplyMarkup());
			case ">>" ->
				editMessage = this.editMessage(chatId, messageId,
						timeSendInlineKeyboardHandler.addHour(messageText), update.getCallbackQuery().getMessage().getReplyMarkup());

			case "backSubscription" -> newMessage = new MessageAdapter().setChatId(chatId).setText(StringConst.SETTINGS_MESSAGE)
					.setInlineKeyboard(new InlineKeyboardConstructor()
							.addInlineButtonInRow("Настроить время отправки сообщения", "setSendingMessageTime")
							.addNewInlineRow().addInlineButtonInRow("Настроить информационные параметры сообщения", "setMessageInfoParameters")
							.addNewInlineRow().addInlineButtonInRow("Настроить форму обращения к пользователю", "setUserForm")
							.getInlineKeyboard());

			case "setTimeSend" -> settingService.saveSetting(userService.findById(userId).getUserSetting().setTimeSend(messageText));

			case "setSendingMessageTime" ->editMessage = this.editMessage(chatId, messageId, StringConst.START_TIME_SEND,
					new InlineKeyboardConstructor()
						.addInlineButtonInRow("<<", "<<")
						.addInlineButtonInRow(">>", ">>")
						.addNewInlineRow().addInlineButtonInRow("Подтвердить", "setTimeSend")
						.getInlineKeyboard()
						);
//				newMessage = new MessageAdapter().setChatId(chatId)
//						.setText(StringConst.START_TIME_SEND)
//						.setInlineKeyboard(new InlineKeyboardConstructor()
//						.addInlineButtonInRow("<<", "<<")
//						.addInlineButtonInRow(">>", ">>")
//						.addNewInlineRow().addInlineButtonInRow("Подтвердить", "setTimeSend")
//						.getInlineKeyboard());

			case "setMessageInfoParameters" -> newMessage = new MessageAdapter().setChatId(chatId).
					setText(StringConst.NEWS_PARAMETERS_MESSAGE)
					.setInlineKeyboard(SettingSubscriptionsKeyboard
					.createInlineKeyboardSettingSubscription(userService.findById(userId).getSubscription()));

			case "setUserForm" -> {
				userStateService.saveUserState(userId, UserStateEnum.WAIT_NAME);
				newMessage = new MessageAdapter().setChatId(chatId).setText(StringConst.INPUT_NAME_FOR_USER);
				editMessage = null;
			}


			default -> this.handleSettingSubscription(callData, userId, editMessage);
	}

		if (newMessage != null) {
			bot.execute(newMessage.getSendMessage());
		}
		if (editMessage != null) {
			bot.execute(editMessage);
		}

	}

	private EditMessageText handleSettingSubscription(String field, Long userId, EditMessageText editMessage) {
		Subscription subscription = userService.findById(userId).getSubscription();
		switch (field) {
			case "weather" -> subscription = subscription.setWeather(!subscription.getWeather());
			case "quote" -> subscription = subscription.setQuote(!subscription.getQuote());
			case "film" -> subscription = subscription.setFilm(!subscription.getFilm());
			case "exchangeRates" -> subscription = subscription.setExchangeRates(!subscription.getExchangeRates());
			case "news" -> subscription = subscription.setNews(!subscription.getNews());
		}
		subscriptionService.saveSubscription(subscription);
		editMessage.setReplyMarkup(SettingSubscriptionsKeyboard.createInlineKeyboardSettingSubscription(subscription));
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
}
