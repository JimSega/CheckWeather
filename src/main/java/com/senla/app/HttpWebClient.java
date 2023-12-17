package com.senla.app;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HttpWebClient {
    @Value("${place.locationName}")
    String locationName;
    @Value("${HttpWebClient.header1}")
    String header1;
    @Value("${HttpWebClient.header2}")
    String header2;
    private final String uri = "https://weatherapi-com.p.rapidapi.com/current.json?q=";
    public String getResponse() {
        return WebClient.create().get()
                .uri(uri + locationName)
                .header("X-RapidAPI-Key", header1)
                .header("X-RapidAPI-Host", header2)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public String getConditionText(String response) {
        String conditionText;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(response);
            JSONObject object1 = (JSONObject) new JSONParser().parse(object.get("current").toString());
            JSONObject object2 = (JSONObject) new JSONParser().parse(object1.get("condition").toString());
            conditionText = object2.get("text").toString();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return conditionText;
    }
}
