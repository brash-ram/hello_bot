package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.entity.cache.Film;
import com.kpd.kpd_bot.jpa.cache.FilmRepository;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class FilmService {
	private final FilmRepository filmRepository;
	private final FilmAPI filmAPI;

	public Film getFilm() {
		Film film = this.getFilm(DateGetter.getSqlDate());
		if (film == null) {
			film = this.getFilmFromApiAndSave();
		} else {
			if (DateGetter.getDifferanceDay(film.getDateUpdate()) > 0) {
				Long id = film.getId();
				film = this.getFilmFromApiAndSave();
				filmRepository.deleteById(id);
			}
		}
		return film;
	}


	private Film getFilm(Date dateUpdate) {
		Film film = filmRepository.findByDateUpdateLessThanEqual(dateUpdate);
		Hibernate.initialize(film);
		return film;
	}

	private Film getFilmFromApiAndSave() {
		return filmRepository.save(filmAPI.getFilm().setDateUpdate(DateGetter.getSqlDate()));
	}
}
