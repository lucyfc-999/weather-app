package com.lu.weather_app.model;

public record WeatherData(
        String city,
        String region,
        String country,
        double tempC,
        double tempF,
        String conditionText,
        String conditionIcon,
        Integer humidity,
        Integer cloud,
        String dateTime
) {
}
