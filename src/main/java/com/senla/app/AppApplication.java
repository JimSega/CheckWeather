package com.senla.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Component
	public class Runner implements CommandLineRunner {
		private final WeatherRepository repository;

		public Runner(WeatherRepository repository) {
			this.repository = repository;
		}

		@Override
		public void run(String... args) {
			doWeHaveSomethingInDb();
			WebClient webClient = WebClient.create();
			String response = webClient.get()
					.uri("https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk")
					.header("X-RapidAPI-Key", "1be3350cf0msh8d651554b7157c7p172623jsnbae0397cb54a")
					.header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
					.retrieve()
					.bodyToMono(String.class)
					.block();
			String responseJson;
			try {
				Object object = new ObjectMapper().readValue(response, Object.class);
				responseJson = new ObjectMapper().writerWithDefaultPrettyPrinter()
						.writeValueAsString(object);
				System.out.println(responseJson);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				JsonFrom jsonFrom = objectMapper.readValue(response, JsonFrom.class);
				System.out.println(jsonFrom);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			try {
				Map<String, Object> weatherMap = objectMapper.readValue(response, new TypeReference<>() {
                });
				System.out.println(weatherMap.get("location"));
				try {
					JSONObject object = (JSONObject) new JSONParser().parse(response);
					System.out.println(object.get("location"));
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			System.exit(0);

		}
		private void doWeHaveSomethingInDb() {
			long count = repository.count();
			if (count > 0) {
				System.out.printf("Db has %d weather(s)%n", count);
			} else {
				System.out.println("Db is empty");
			}
		}
	}

}
