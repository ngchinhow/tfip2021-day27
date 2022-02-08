package com.tfip2021.module3.controllers;

import com.tfip2021.module3.service.OpenWeatherMapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class WeatherController {

    @Autowired
    private OpenWeatherMapService service;

    @GetMapping(
        path="/weather/{city}",
        produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getCityWeather(
        @PathVariable(name = "city", required = true) String city
    ) {
        return new ResponseEntity<String>(
            service.getCurrentWeather(city),
            HttpStatus.OK
        );
    }
}
