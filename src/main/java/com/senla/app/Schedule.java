package com.senla.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@EnableScheduling
@Component
public class Schedule {
    @Value("${place.locationName}")
    String locationName;
    @Value("${place.id}")
    int placeId;
    final int FIXED_TIME = 600000;
    private final WeatherRepository repository;
    private final HttpWebClient httpWebClient;

    public Schedule(WeatherRepository repository, HttpWebClient httpWebClient) {
        this.repository = repository;
        this.httpWebClient = httpWebClient;
    }

    @Scheduled(fixedDelay = FIXED_TIME)
    public void checkWeather() {
        String response = httpWebClient.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JsonFromResponseWeather jsonFrom = objectMapper.readValue(response, JsonFromResponseWeather.class);
            Weather weather = objectMapper.readValue(jsonFrom.getCurrent().toJSONString(), Weather.class);
            weather.setLocalDate(LocalDate.now());
            weather.setLocalTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
            weather.setPlace(placeId);
            weather.setLocationName(locationName);
            weather.setCondition(httpWebClient.getConditionText(response));
            repository.save(weather);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}
