package com.lu.weather_app.controller;

import com.lu.weather_app.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("city") String city) {
        if (city == null || city.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Ingrese un nombre válido de una ciudad.");
        }

        if (!city.matches("[a-zA-Z\\s]+")) {
            return ResponseEntity.badRequest().body("Ingrese un nombre válido de una ciudad.");
        }

        city = city.trim().toLowerCase();
        city = Character.toUpperCase(city.charAt(0)) + city.substring(1);

        try {
            var weatherData = service.getWeather(city);
            return ResponseEntity.ok(weatherData);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró información para la ciudad: " + city);
        }
    }
}
