package com.senla.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final WeatherRepository repository;
    private final Logger logger = LoggerFactory.getLogger(AppApplication.class);
    public RestController(WeatherRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/weather")
    public String checkWeather() {
        if (repository.findLastRecord().isPresent()) {
            logger.info("check last record");
            return repository.findLastRecord().get().toString();
        }
        throw new NullPointerException();
    }

    @PostMapping("/average")
    public Map<String, Double> checkAverage(@RequestBody BodyRequestAverage bodyRequestAverage) {
        if(bodyRequestAverage.from().isBefore(bodyRequestAverage.to()) ||
        bodyRequestAverage.from().isEqual(bodyRequestAverage.to())) {
            if(repository.findAverageTemp(bodyRequestAverage.from(), bodyRequestAverage.to()).isPresent()) {
                logger.info("check average temp");
                return Map.of("average_temp",
                        repository.findAverageTemp(bodyRequestAverage.from(), bodyRequestAverage.to()).get());
            } else {
                throw new NullPointerException();
            }

        }
        throw new DateException("Wrong date!");
    }
}
