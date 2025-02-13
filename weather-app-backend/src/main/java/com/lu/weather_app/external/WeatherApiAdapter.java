package com.lu.weather_app.external;

import com.lu.weather_app.external.model.WeatherApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Optional;

@Component
public class WeatherApiAdapter {

    private final WebClient webClient;
    private static final String BASE_URL = "url-API";

    public WeatherApiAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://api.weatherapi.com")
                .build();
    }

    public Optional<WeatherApiResponse> getWeather(String city) {
        String url = String.format(BASE_URL + "&q=%s&aqi=no", city);
        try {
            WeatherApiResponse response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(WeatherApiResponse.class)
                    .block();
            return Optional.ofNullable(response);
        } catch (Exception e) {
            System.err.println("Error al obtener el clima: " + e.getMessage());
            return Optional.empty();
        }
    }
}


