package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.jpa.SubscriptionRepository;
import com.kpd.kpd_bot.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;

	private final UserRepository userRepository;

	public Subscription saveNewSubscription() {
		return subscriptionRepository.save(new Subscription(null, true, true, true, true, true));
	}

	public Subscription getSubscriptionByUserId(Long userId) {
		return userRepository.findById(userId).get().getSubscription();
	}

	public Subscription saveSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}
}
