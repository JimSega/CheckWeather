package com.senla.app;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonFrom {
    private JSONObject location;

    public JsonFrom() {
    }

    public JsonFrom(JSONObject location, JSONObject current) {
        this.location = location;
        this.current = current;
    }

    private JSONObject current;

    public JSONObject getLocation() {
        return location;
    }

    public void setLocation(JSONObject location) {
        this.location = location;
    }

    public JSONObject getCurrent() {
        return current;
    }

    public void setCurrent(JSONObject current) {
        this.current = current;
    }
    public String toString() {
        return "location: " + this.getLocation() + "\ncurrent: " + this.getCurrent();
    }
}
