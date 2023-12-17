package com.senla.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Value("${place.locationName}")
    String locationName;
    @Value("${place.id}")
    int placeId;
    private final WeatherRepository repository;
    private final HttpWebClient httpWebClient;
    public RestController(WeatherRepository repository, HttpWebClient httpWebClient) {
        this.repository = repository;
        this.httpWebClient = httpWebClient;
    }
    @GetMapping("/weather")
    public synchronized String checkWeather() {
        String answer;
        String response = httpWebClient.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JsonFromResponseWeather jsonFrom = objectMapper.readValue(response, JsonFromResponseWeather.class);
            Weather weather = objectMapper.readValue(jsonFrom.getCurrent().toJSONString(), Weather.class);
            weather.setLocalDate(LocalDate.now());
            weather.setLocalTime(LocalTime.now());
            weather.setPlace(placeId);
            weather.setLocationName(locationName);
            weather.setCondition(httpWebClient.getConditionText(response));
            System.out.println(weather);
            repository.save(weather);
            answer = "application start";
        } catch (JsonProcessingException e) {
            answer = "ERROR";
            System.out.println(e.getMessage());
        }
        return answer;
    }
}
