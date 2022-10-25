package com.kpd.kpd_bot.api.weather;

import com.kpd.kpd_bot.entity.cache.weather.BaseWeather;
import com.kpd.kpd_bot.jpa.cache.BaseWeatherRepository;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class WeatherService {
	private final WeatherAPI weatherAPI;
	private final BaseWeatherRepository baseWeatherRepository;

	public BaseWeather getWeather(String city) {
		BaseWeather baseWeather = this.getWeather(DateGetter.getTimestamp());

		if (baseWeather == null) {
			baseWeather = this.getWeatherFromApiAndSave(city);
		} else {
			if (DateGetter.getDifferanceHour(baseWeather.getTimeUpdate()) > 0) {
				Long id = baseWeather.getId();
				baseWeather = this.getWeatherFromApiAndSave(city);
				baseWeatherRepository.deleteById(id);
			}
		}

		return baseWeather;
	}


	private BaseWeather getWeather(Timestamp dateUpdate) {
		BaseWeather BaseWeather = baseWeatherRepository.findByTimeUpdateLessThanEqual(dateUpdate);
		Hibernate.initialize(BaseWeather);
		return BaseWeather;
	}

	private BaseWeather getWeatherFromApiAndSave(String city) {
		return baseWeatherRepository.save(weatherAPI.getWeather(city).setTimeUpdate(DateGetter.getTimestamp()));
	}
}
