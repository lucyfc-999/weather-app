package com.lu.weather_app.external.model;

public record WeatherApiResponse(
        Location location,
        Current current
) {
}
