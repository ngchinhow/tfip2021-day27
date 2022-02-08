package com.tfip2021.module3.service;

import java.io.StringReader;

import com.tfip2021.module3.models.City;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import static com.tfip2021.module3.Constants.OPENWEATHERMAP_BASE_URI;
import static com.tfip2021.module3.Constants.ENV_WEATHER_API_KEY;

@Service
public class OpenWeatherMapService {

    public String getCurrentWeather(String city) {
        System.out.println(city);
        final String weatherAPIKey = System.getenv(ENV_WEATHER_API_KEY);
        String url = UriComponentsBuilder.
            fromUriString(OPENWEATHERMAP_BASE_URI).
            pathSegment("weather").
            queryParam("q", city.trim().replaceAll(" ", "+")).
            queryParam("appid", weatherAPIKey).
            encode().
            toUriString();
        return City.fromJson(makeGetRequest(url)).toJsonString();
    }

    public JsonObject makeGetRequest(String url) {
        RequestEntity<Void> req = RequestEntity.
            get(url).
            build();
        System.out.println(url);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        return reader.readObject();
    }
}
