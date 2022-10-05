package com.kpd.kpd_bot.api.quote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuoteResponseDTO {
	private String q;
	private String a;
}
