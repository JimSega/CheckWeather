package com.senla.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		private final WeatherRepository repository;
		private final HttpWebClient httpWebClient;

		public Runner(WeatherRepository repository, HttpWebClient httpWebClient) {
			this.repository = repository;
			this.httpWebClient = httpWebClient;
		}

		@Override
		public void run(String... args) {
			String response = httpWebClient.getResponse();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				JsonFromResponseWeather jsonFrom = objectMapper.readValue(response, JsonFromResponseWeather.class);
				Weather weather = objectMapper.readValue(jsonFrom.getCurrent().toJSONString(), Weather.class);
				weather.setLocalDate(LocalDate.now());
				weather.setLocalTime(LocalTime.now());
				weather.setPlace(1);
				weather.setLocationName("Minsk");
				weather.setCondition(httpWebClient.getConditionText(response));
				System.out.println(weather);
				repository.save(weather);


			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}

			//System.exit(0);

		}
	}

}
