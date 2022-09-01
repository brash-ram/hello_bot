package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}