package com.kpd.kpd_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "exchange_rates_setting")
public class ExchangeRatesSetting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "CHF_RUB", nullable = false)
	private Boolean CHF_RUB;

	@Column(name = "JPY_RUB", nullable = false)
	private Boolean JPY_RUB;

	@Column(name = "EUR_RUB", nullable = false)
	private Boolean EUR_RUB;

	@Column(name = "CNY_RUB", nullable = false)
	private Boolean CNY_RUB;

	@Column(name = "USD_RUB", nullable = false)
	private Boolean USD_RUB;

	@Column(name = "GBP_RUB", nullable = false)
	private Boolean GBP_RUB;
}
