package com;

public class Temperature
{
    private String period;
    private Double temperature;

    public Temperature(String period, Double temperature) {
        this.period = period;
        this.temperature = temperature;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public int getBeginTime(){
        return Integer.parseInt(period.split("-")[0]);
    }

    public int getEndTime(){
        return Integer.parseInt(period.split("-")[1]);
    }
}
