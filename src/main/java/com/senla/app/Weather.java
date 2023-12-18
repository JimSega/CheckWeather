package com.senla.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "archive")
public class Weather {
    public Weather() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @JsonIgnore
    private int place;
    @JsonProperty("condition")
    @Transient
    @JsonIgnore
    private JsonNode JsonNode;
    @Column(name = "temp_c")
    @JsonProperty("temp_c")
    private double tempC;
    @Column(name = "wind_mph")
    @JsonProperty("wind_mph")
    private double windMph;
    @Column(name = "pressure_mb")
    @JsonProperty("pressure_mb")
    private double pressureMb;
    private double humidity;
    @Column(name = "condition_text")
    @JsonIgnore
    private String condition;
    @Column(name = "location_name")
    @JsonIgnore
    private String locationName;
    @JsonIgnore
    @Column(name = "date_check")
    private LocalDate localDate;
    @JsonIgnore
    @Column(name = "time_check")
    private LocalTime localTime;

    public Weather(Integer id, int place, double tempC, double windMph, double pressureMb, double humidity,
                   String condition, String locationName, LocalDate localDate, LocalTime localTime) {
        this.id = id;
        this.place = place;
        this.tempC = tempC;
        this.windMph = windMph;
        this.pressureMb = pressureMb;
        this.humidity = humidity;
        this.condition = condition;
        this.locationName = locationName;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public double getWindMph() {
        return windMph;
    }

    public void setWindMph(double windMph) {
        this.windMph = windMph;
    }

    public double getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(double pressureMb) {
        this.pressureMb = pressureMb;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public com.fasterxml.jackson.databind.JsonNode getJsonNode() {
        return JsonNode;
    }

    public void setJsonNode(com.fasterxml.jackson.databind.JsonNode jsonNode) {
        JsonNode = jsonNode;
    }
    public String toString() {
        return ("temp_c: " + tempC + "\nwind_mph: " + windMph + "\npressureMb: " + pressureMb + "\nhumidity: " +
                humidity + "\nlocation: " + locationName + "\ntime: " + localTime + "\ndate: " + localDate +
                "\ncondition_text: " + condition);
    }
}
