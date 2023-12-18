package com.senla.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDate;
import java.util.Map;


class RestControllerTest extends AbstractTest{

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void checkWeather() {
        String uri = "/weather";
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            Assertions.assertEquals(200, status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkAverage() {
        String uri = "/average";
        Map<String, String> mapRequest = Map.of("from", LocalDate.now().minusDays(1).toString(),
                "to", LocalDate.now().toString());
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(super.mapToJson(mapRequest))).andReturn();
            int status = mvcResult.getResponse().getStatus();
            Assertions.assertEquals(200, status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void checkAverageWrongDate() throws Exception {
        String uri = "/average";
        Map<String, String> mapRequest = Map.of("from", "2023-12-32", "to", "2023-12-32");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(mapRequest)))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(response.contains("Wrong format date!"));
    }
    @Test
    void checkAverageFuture() throws Exception {
        String uri = "/average";
        Map<String, String> mapRequest = Map.of("from", LocalDate.now().plusDays(1).toString(),
                "to", LocalDate.now().plusDays(1).toString());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(mapRequest)))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(response.contains("The database does not contain records (records for the required period)"));
    }

    @Test
    void checkAverageWrongPeriod() throws Exception {
        String uri = "/average";
        Map<String, String> mapRequest = Map.of("from", "2023-12-12", "to", "2023-12-11");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(mapRequest)))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(response.contains("Wrong  period!"));
    }
}