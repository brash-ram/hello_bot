package com.kpd.kpd_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "subscription")
public class Subscription {
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private boolean weather;

	@Column(nullable = false)
	private boolean quote;

	@Column(nullable = false)
	private boolean news;

	@Column(nullable = false)
	private boolean film;

	@Column(name = "exchange_rates", nullable = false)
	private boolean exchange_rates;
}