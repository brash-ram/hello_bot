package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.statics.Buttons;
import com.kpd.kpd_bot.statics.StringConst;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageHandler {

	public static MessageAdapter handleMessage(Update update){
		Message message = update.getMessage();
		String messageText = message.getText();
		MessageAdapter newMessage = new MessageAdapter();


		switch (messageText) {
			case "/start":
				newMessage.setChatId(message.getChatId())
						.setText(StringConst.startMessage)
						.addReplyButtons(Buttons.startButtons);
				break;

			default:
				newMessage.setChatId(message.getChatId())
						.setText(StringConst.hui);
				break;
		}
		return newMessage;
	}
}
