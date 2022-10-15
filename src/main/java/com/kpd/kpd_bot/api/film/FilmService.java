package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.api.film.model.Genres;
import com.kpd.kpd_bot.entity.cache.Film;
import com.kpd.kpd_bot.jpa.cache.FilmRepository;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
	private final FilmRepository filmRepository;
	private final FilmAPI filmAPI;

	public Film getFilm() {
		long l = System.currentTimeMillis();
		Film film = this.getFilmFromCache(DateGetter.getSqlDate());
		System.out.println(System.currentTimeMillis()-l);
		if (film == null) {
			film = filmAPI.getFilm();
			film.setDateUpdate(DateGetter.getSqlDate());
			film = filmRepository.save(film);
		}
		return film;
	}

	@Cacheable
	private Film getFilmFromCache(Date dateUpdate) {
		Film film = filmRepository.findByDateUpdateLessThanEqual(dateUpdate);
		Hibernate.initialize(film);
		return film;
	}
}
