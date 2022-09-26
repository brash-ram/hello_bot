package com.kpd.kpd_bot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class InlineKeyboardHandler {

	public MessageAdapter handleMessage(Update update) {
		String call_data = update.getCallbackQuery().getData();
		long message_id = update.getCallbackQuery().getMessage().getMessageId();
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
		MessageAdapter newMessage = new MessageAdapter().setChatId(chat_id);

//		switch (call_data) {
//			case  -> ;
//
//		}

		return newMessage;
	}
}
