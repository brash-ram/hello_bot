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
@Table(name = "subscription")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private Boolean weather;

	@Column(nullable = false)
	private Boolean quote;

	@Column(nullable = false)
	private Boolean news;

	@Column(nullable = false)
	private Boolean film;

	@Column(name = "exchange_rates", nullable = false)
	private Boolean exchangeRates;
}