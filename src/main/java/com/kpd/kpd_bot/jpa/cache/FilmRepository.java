package com.kpd.kpd_bot.jpa.cache;

import com.kpd.kpd_bot.entity.cache.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface FilmRepository extends JpaRepository<Film, Long> {

	Film findByDateUpdateLessThanEqual(Date dateUpdate);

	@Override
	<S extends Film> S save(S entity);
}