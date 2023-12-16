package com.senla.app;

import org.json.simple.JSONObject;

public class JsonFromResponse {
    private JSONObject location;

    public JsonFromResponse() {
    }

    public JsonFromResponse(JSONObject location, JSONObject current) {
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
