package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}