package com.lu.weather_app.external.model;

public record Location(
        String name,
        String region,
        String country,
        String localtime
) {
}

