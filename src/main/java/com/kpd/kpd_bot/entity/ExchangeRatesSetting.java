package com.kpd.kpd_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "exchange_rates_setting")
public class ExchangeRatesSetting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "source_currency", nullable = false)
	private String sourceCurrency;

	@Column(name = "target_currency", nullable = false)
	private String targetCurrency;

}
