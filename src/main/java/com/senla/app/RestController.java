package com.senla.app;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final WeatherRepository repository;
    public RestController(WeatherRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/weather")
    public synchronized String checkWeather() {
        return repository.findLastRecord().toString();
    }
}
