package com.kpd.kpd_bot.api.weather;

import com.kpd.kpd_bot.entity.cache.weather.BaseWeather;
import com.kpd.kpd_bot.jpa.cache.weather.BaseWeatherRepository;
import com.kpd.kpd_bot.jpa.cache.weather.WeatherMainRepository;
import com.kpd.kpd_bot.jpa.cache.weather.WeatherRepository;
import com.kpd.kpd_bot.jpa.cache.weather.WindRepository;
import com.kpd.kpd_bot.service.UserService;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class WeatherService {
	private final WeatherAPI weatherAPI;
	private final BaseWeatherRepository baseWeatherRepository;
	private final WeatherRepository weatherRepository;
	private final WeatherMainRepository weatherMainRepository;
	private final WindRepository windRepository;

	public BaseWeather getWeather(String city, String userId) {
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
		BaseWeather weather = weatherAPI.getWeather(city).setTimeUpdate(DateGetter.getTimestamp());
		this.saveAllObjectInBaseWeather(weather);
		return baseWeatherRepository.save(weather);
	}

	private void saveAllObjectInBaseWeather(BaseWeather baseWeather) {
		baseWeather.setWeather(weatherRepository.saveAll(baseWeather.getWeather()));
		baseWeather.setMain(weatherMainRepository.save(baseWeather.getMain()));
		baseWeather.setWind(windRepository.save(baseWeather.getWind()));
	}
}
