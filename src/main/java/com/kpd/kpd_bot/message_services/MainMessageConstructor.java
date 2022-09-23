package com.kpd.kpd_bot.message_services;

import com.kpd.kpd_bot.statics.StringConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class MainMessageConstructor {
	private final Adapter weatherAdapter;
	private final Adapter quoteAdapter;

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(StringConst.helloMessage).append("\n")
				.append(quoteAdapter.getTextFromMessageService()).append("\n");
		return sb.toString();
	}
}
