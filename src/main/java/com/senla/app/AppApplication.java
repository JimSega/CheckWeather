package com.senla.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@Component
	public static class Runner implements CommandLineRunner {
		@Value("${place.locationName}")
		String locationName;
		@Value("${place.id}")
		int placeId;
		private final WeatherRepository repository;
		private final HttpWebClient httpWebClient;

		public Runner(WeatherRepository repository, HttpWebClient httpWebClient) {
			this.repository = repository;
			this.httpWebClient = httpWebClient;
		}

		@Override
		public void run(String... args) {
			while (true) {
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
					repository.save(weather);
				} catch (JsonProcessingException e) {
					System.out.println(e.getMessage());
				}
				try {
					Thread.sleep(600000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

		}
	}
}
