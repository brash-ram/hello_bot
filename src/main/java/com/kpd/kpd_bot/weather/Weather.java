package com.kpd.kpd_bot.weather;

import com.kpd.kpd_bot.dto.response.WeatherResponseDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, WeatherResponseDTO weatherModel) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=9ebc03d0692acbf40d256fd4ff250d87");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        weatherModel.setCity(object.getString("city"));
        weatherModel.setTemperature(object.getDouble("temp"));
        weatherModel.setHumidity(object.getDouble("humidity"));
        weatherModel.setWindSpeed(object.getDouble("speed"));


        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            weatherModel.setIcon((String) obj.get("icon"));
            weatherModel.setDescription((String) obj.get("description"));
        }

        return "Weather now in " + weatherModel.getCity() + "\n" +
                "Temperature:  " + weatherModel.getTemperature() + "°C" + "\n" +
                "Description: " + weatherModel.getDescription() + "\n" +
                "Wind speed: " + weatherModel.getWindSpeed() + " km/h" + "\n" +
                "Humidity: " + weatherModel.getHumidity() + "%" + "\n" +
                "https://raw.githubusercontent.com/mykytam/weatherTelegramBot/master/img/" +
                weatherModel.getIcon() + ".png";
    }
}
