package com.lu.weather_app.exceptions;

public class InvalidCityNameException extends RuntimeException {
    public InvalidCityNameException(String message) {
        super(message);
    }
}

