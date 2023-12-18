package com.senla.app;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
            Assert.assertEquals(200, status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}