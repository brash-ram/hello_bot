package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserStateRepository extends JpaRepository<UserState, Long> {
	UserState findByUserId(Long userId);
	@Transactional
	void deleteByUserId(Long userId);

	boolean existsByUserId(Long userId);
}