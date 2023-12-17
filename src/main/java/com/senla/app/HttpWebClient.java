package com.senla.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class HttpWebClient {
    private final String uri = "https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk";
    public String getResponse() {
        return WebClient.create().get()
                .uri(uri)
                .header("X-RapidAPI-Key", "1be3350cf0msh8d651554b7157c7p172623jsnbae0397cb54a")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public String strToJson(String response) {
        String responseJson;
        try {
            Object object = new ObjectMapper().readValue(response, Object.class);
            responseJson = new ObjectMapper().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
            System.out.println(responseJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return responseJson;
    }
    public String getConditionText(String response) {
        String conditionText = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Map<String, Object> weatherMap = objectMapper.readValue(response, new TypeReference<>() {});
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(response);
                JSONObject object1 = (JSONObject) new JSONParser().parse(object.get("current").toString());
                JSONObject object2 = (JSONObject) new JSONParser().parse(object1.get("condition").toString());
                conditionText = object2.get("text").toString();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return conditionText;
    }
}
