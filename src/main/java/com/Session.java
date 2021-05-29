package com;

public class Session {
    private String sessionId;
    private Double temperature;
    private Boolean boilerState;
    private Boolean airConditionerState;

    public Session(String sessionId, Double temperature, Boolean boilerState, Boolean airConditionerState) {
        this.sessionId = sessionId;
        this.temperature = temperature;
        this.boilerState = boilerState;
        this.airConditionerState = airConditionerState;
    }

    public String getSessionid() {
        return sessionId;
    }

    public void setSessionid(String sessionId) {
        this.sessionId = sessionId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getBoilerState() {
        return boilerState;
    }

    public void setBoilerState(Boolean boilerState) {
        this.boilerState = boilerState;
    }

    public Boolean getAirConditionerState() {
        return airConditionerState;
    }

    public void setAirConditionerState(Boolean airConditionerState) {
        this.airConditionerState = airConditionerState;
    }
}
