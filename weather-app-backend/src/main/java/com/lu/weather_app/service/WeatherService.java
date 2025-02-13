package com.lu.weather_app.service;

import com.lu.weather_app.exceptions.CityNotFoundException;
import com.lu.weather_app.exceptions.InvalidCityNameException;
import com.lu.weather_app.external.WeatherApiAdapter;
import com.lu.weather_app.external.model.WeatherApiResponse;
import com.lu.weather_app.model.WeatherData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    private final WeatherApiAdapter weatherApiAdapter;

    public WeatherService (WeatherApiAdapter weatherApiAdapter){
        this.weatherApiAdapter = weatherApiAdapter;
    }

    public WeatherData getWeather(String city) {
        city = city.trim();

        if (!city.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ ]+")) {
            throw new InvalidCityNameException("Ingrese un nombre de ciudad válido.");
        }

        city = normalizeCityName(city);

        var dataResponse = weatherApiAdapter.getWeather(city);
        if (dataResponse.isEmpty()) {
            throw new CityNotFoundException("No se encontró información para la ciudad: " + city);
        }

        return mapTo(dataResponse.get());
    }

    private String normalizeCityName(String city) {
        city = city.toLowerCase();
        return city.substring(0, 1).toUpperCase() + city.substring(1);
    }

    private WeatherData mapTo(WeatherApiResponse data) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(data.location().localtime(), inputFormatter);

        String formattedDateTime = dateTime.format(outputFormatter);

        return new WeatherData(
                data.location().name(),
                data.location().region(),
                data.location().country(),
                data.current().tempC(),
                data.current().tempF(),
                data.current().condition().text(),
                "https:" + data.current().condition().icon(),
                data.current().humidity(),
                data.current().cloud(),
                formattedDateTime
        );
    }
}
