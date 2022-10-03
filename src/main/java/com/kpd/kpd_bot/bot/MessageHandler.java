package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.service.MainMessageConstructor;
import com.kpd.kpd_bot.service.UserService;
import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import com.kpd.kpd_bot.util.InlineKeyboardConstructor;
import com.kpd.kpd_bot.util.SettingSubscriptionsKeyboard;
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
		Long userId = update.getMessage().getFrom().getId();

		if (!userService.existUser(userId)) {
			userService.saveNewUser(update.getMessage().getFrom());
		}

		switch (messageText) {
			case "/start" -> newMessage.setText(StringConst.startMessage)
					.addReplyButtons(Buttons.startButtons);
			case "Получить новости этого дня прямо сейчас" -> newMessage.setText(mainMessageConstructor.getMessage(userId));
			case "Настройки" -> newMessage.setText(StringConst.settingsMessage)
					.addReplyButtons(Buttons.settingsButtons);
			case "Настроить время отправки сообщения" -> newMessage.setText(StringConst.startTimeSend)
					.setInlineKeyboard(new InlineKeyboardConstructor()
							.addInlineButtonInRow("<<", "<<")
							.addInlineButtonInRow(">>", ">>")
							.addNewInlineRow().addInlineButtonInRow("Подтвердить", "setTimeSend")
							.getInlineKeyboard())
			;
			case "Настройка информационных параметров сообщения" -> newMessage.setText(StringConst.newsParametersMessage)
					.setInlineKeyboard(SettingSubscriptionsKeyboard.createInlineKeyboardSettingSubscription(userService.findById(userId).getSubscription()));
			default -> newMessage.setText(StringConst.defaultMessage);
		}
		bot.execute(newMessage.getSendMessage());
	}
}
