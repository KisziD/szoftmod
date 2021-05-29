package com;

import java.util.ArrayList;

public class Subscriber {
    private String subscriber;
    private String homeId;
    private String boilerType;
    private String airConditionerType;
    private ArrayList<Temperature> temperatures;

    public Subscriber(String subscriber, String homeId, String boilerType, String airConditionerType, ArrayList<Temperature> temperatures) {
        this.subscriber = subscriber;
        this.homeId = homeId;
        this.boilerType = boilerType;
        this.airConditionerType = airConditionerType;
        this.temperatures = temperatures;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getBoilerType() {
        return boilerType;
    }

    public void setBoilerType(String boilerType) {
        this.boilerType = boilerType;
    }

    public String getAirConditionerType() {
        return airConditionerType;
    }

    public void setAirConditionerType(String airConditionerType) {
        this.airConditionerType = airConditionerType;
    }

    public ArrayList<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(ArrayList<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

}
