package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.jpa.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;

	public Subscription saveNewSubscription() {
		return subscriptionRepository.save(new Subscription(null, false, true, true, true, false));
	}
}