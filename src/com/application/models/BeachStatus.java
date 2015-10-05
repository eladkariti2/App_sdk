package com.application.models;

/**
 * Created by elad on 10/4/2015.
 */
public class BeachStatus {

    private FlagType flagType;
    private String temperature;
    private String waveHeight;
    private String wind;


    public FlagType getFlagType() {
        return flagType;
    }

    public void setFlagType(FlagType flagType) {
        this.flagType = flagType;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(String waveHeight) {
        this.waveHeight = waveHeight;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
