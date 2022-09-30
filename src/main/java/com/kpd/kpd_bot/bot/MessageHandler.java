package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.service.MainMessageConstructor;
import com.kpd.kpd_bot.service.UserService;
import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import com.kpd.kpd_bot.util.TimeSendInlineKeyboardHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class MessageHandler {
	private final MainMessageConstructor mainMessageConstructor;
	private final TimeSendInlineKeyboardHandler timeSendInlineKeyboardHandler;
	private final UserService userService;

	public void handleMessage(Update update, Bot bot) throws TelegramApiException {
		Message message = update.getMessage();
		String messageText = message.getText();
		MessageAdapter newMessage = new MessageAdapter().setChatId(message.getChatId());


		switch (messageText) {
			case "/start" -> {
				newMessage.setText(StringConst.startMessage)
					.addReplyButtons(Buttons.startButtons);
				userService.saveNewUser(update.getMessage().getFrom());
			}
			case "Получить новости этого дня прямо сейчас" -> newMessage.setText(mainMessageConstructor.getMessage())
					.addInlineButtonInRow("but 1", "but_1").addNewInlineRow()
					.addInlineButtonInRow("but 2", "but_2").addInlineButtonInRow("but 3", "but_3");
			case "Настройки" -> newMessage.setText(StringConst.settingsMessage)
					.addReplyButtons(Buttons.settingsButtons);
			case "Настроить время отправки сообщения" -> newMessage.setText(StringConst.startTimeSend)
					.setInlineKeyboard(timeSendInlineKeyboardHandler.getKeyboard());
			default -> newMessage.setText(StringConst.defaultMessage);
		}
		bot.execute(newMessage.getSendMessage());
	}
}
