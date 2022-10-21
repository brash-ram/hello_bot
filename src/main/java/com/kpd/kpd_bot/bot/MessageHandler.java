package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.entity.UserState;
import com.kpd.kpd_bot.myenum.UserStateEnum;
import com.kpd.kpd_bot.service.MainMessageConstructor;
import com.kpd.kpd_bot.service.SettingService;
import com.kpd.kpd_bot.service.UserService;
import com.kpd.kpd_bot.service.UserStateService;
import com.kpd.kpd_bot.statics.ReplyButtons;
import com.kpd.kpd_bot.statics.StringConst;
import com.kpd.kpd_bot.util.InlineKeyboardConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MessageHandler {
	private final MainMessageConstructor mainMessageConstructor;
	private final UserService userService;
	private final SettingService settingService;
	private final UserStateService userStateService;

	public void handleMessage(Update update, Bot bot) throws TelegramApiException, UnsupportedEncodingException {
		Message message = update.getMessage();
		String messageText = message.getText();
		MessageAdapter newMessage = new MessageAdapter().setChatId(message.getChatId());
		Long userId = update.getMessage().getFrom().getId();

		if (!userService.existUser(userId)) {
			userService.saveNewUser(update.getMessage().getFrom());
		}

		switch (messageText) {
			case "/start" -> newMessage.setText(StringConst.START_MESSAGE)
					.addReplyButtons(ReplyButtons.startButtons);

			case "Получить новости этого дня прямо сейчас" -> newMessage.setText(mainMessageConstructor.getMessage(userId));

			case "Настройки" -> newMessage.setText(StringConst.SETTINGS_MESSAGE)
					.setInlineKeyboard(new InlineKeyboardConstructor()
							.addInlineButtonInRow("Настроить время отправки сообщения", "setSendingMessageTime")
							.addNewInlineRow().addInlineButtonInRow("Настроить информационные параметры сообщения", "setMessageInfoParameters")
							.addNewInlineRow().addInlineButtonInRow("Настроить форму обращения в приветствии", "setUserForm")
							.getInlineKeyboard());

			case "Справка" -> newMessage.setText(StringConst.HELP_MESSAGE);

			default -> {
				UserState userState = userStateService.findUserState(userId);
				if (userState != null) {
					newMessage = this.handleMessageByUserState(update, userState.getUserState());
				}
			}
		}
		bot.execute(newMessage.getSendMessage());
	}

	private MessageAdapter handleMessageByUserState(Update update, UserStateEnum userStateEnum) {
		Message message = update.getMessage();
		String messageText = message.getText();
		MessageAdapter newMessage = new MessageAdapter().setChatId(message.getChatId());
		Long userId = update.getMessage().getFrom().getId();
		switch (userStateEnum) {
			case WAIT_NAME -> {
				userService.saveUser(userService.findById(userId).setName(messageText));
				newMessage.setText(StringConst.GOOD_INPUT_NAME_FOR_USER);
				userStateService.deleteUserState(userId);
			}
			case WAIT_CITY-> {
				settingService.saveSetting(userService.findById(userId).getUserSetting().setCity(messageText));
				newMessage.setText(StringConst.GOOD_INPUT_CITY_FOR_USER);
				userStateService.deleteUserState(userId);
			}
		}
		return newMessage;
	}
}
