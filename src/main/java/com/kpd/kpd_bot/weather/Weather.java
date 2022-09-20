package com.kpd.kpd_bot.weather;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, WeatherModel model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=KEYHERE");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setCity(object.getString("city"));
        model.setTemperature(object.getDouble("temp"));
        model.setHumidity(object.getDouble("humidity"));
        model.setWindSpeed(object.getDouble("speed"));


        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setDescription((String) obj.get("description"));
        }

        return "Weather now in " + model.getCity() + "\n" +
                "Temperature:  " + model.getTemperature() + "°C" + "\n" +
                "Description: " + model.getDescription() + "\n" +
                "Wind speed: " + model.getWindSpeed() + " km/h" + "\n" +
                "Humidity: " + model.getHumidity() + "%" + "\n" +
                //"http://openweathermap.org/img/w/" + model.getIcon() + ".png";
                "https://raw.githubusercontent.com/mykytam/weatherTelegramBot/master/img/" + model.getIcon() + ".png";
    }
}
