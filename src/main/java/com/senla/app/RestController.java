package com.senla.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final WeatherRepository repository;
    public RestController(WeatherRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/weather")
    public String checkWeather() {
        return repository.findLastRecord().toString();
    }

    @PostMapping("/average")
    public Map<String, Double> checkAverage(@RequestBody BodyRequestAverage bodyRequestAverage) {
        return Map.of("average_temp", repository.findAverageTemp(bodyRequestAverage.from(), bodyRequestAverage.to()));

    }
}
