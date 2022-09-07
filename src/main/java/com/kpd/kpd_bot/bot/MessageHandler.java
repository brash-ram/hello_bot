package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.message_services.MainMessageConstructor;
import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageHandler {

	public static MessageAdapter handleMessage(Update update){
		Message message = update.getMessage();
		String messageText = message.getText();
		MessageAdapter newMessage = new MessageAdapter().setChatId(message.getChatId());


		switch (messageText) {
			case "/start":
				newMessage.setText(StringConst.startMessage)
						.addReplyButtons(Buttons.startButtons);
				break;

			case "Приветственное сообщение":
				newMessage.setText(MainMessageConstructor.getMessage());
				break;

			default:
				newMessage.setText(StringConst.hui);
				break;
		}
		return newMessage;
	}
}
