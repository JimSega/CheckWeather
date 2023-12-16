package com.senla.app;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "archive")
public class Weather {
    public Weather() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int place;
    @Column(name = "temp_c")
    private double tempC;
    @Column(name = "wind_mph")
    private double windMph;
    @Column(name = "pressure_mb")
    private double pressureMb;
    private double humidity;
    @Column(name = "condition_text")
    private String condition;
    @Column(name = "location_name")
    private String locationName;
    @Column(name = "date_check")
    private LocalDate localDate;
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
}