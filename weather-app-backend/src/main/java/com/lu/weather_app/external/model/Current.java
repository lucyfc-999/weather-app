package com.lu.weather_app.external.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Current(
        @JsonProperty("temp_c")
        Double tempC,
        @JsonProperty("temp_f")
        Double tempF,
        Condition condition,
        Integer humidity,
        Integer cloud
) {
}

