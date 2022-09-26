package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.service.MainMessageConstructor;
import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
@Service
@RequiredArgsConstructor
public class MessageHandler {
	private final MainMessageConstructor mainMessageConstructor;

	public MessageAdapter handleMessage(Update update){
		Message message = update.getMessage();
		String messageText = message.getText();
		MessageAdapter newMessage = new MessageAdapter().setChatId(message.getChatId());


		switch (messageText) {
			case "/start" -> newMessage.setText(StringConst.startMessage)
					.addReplyButtons(Buttons.startButtons);
			case "Получить новости этого дня прямо сейчас" -> newMessage.setText(mainMessageConstructor.getMessage())
					.addInlineButtonInRow("but 1", "but_1").addNewInlineRow()
					.addInlineButtonInRow("but 2", "but_2").addInlineButtonInRow("but 3", "but_3");
			case "Настройки" -> newMessage.setText(StringConst.settingsMessage)
					.addReplyButtons(Buttons.settingsButtons);
			default -> newMessage.setText(StringConst.defaultMessage);
		}
		return newMessage;
	}
}
