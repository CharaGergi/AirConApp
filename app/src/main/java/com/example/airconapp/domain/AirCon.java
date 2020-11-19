package com.example.airconapp.domain;

public class AirCon
{
    private String name;
    private int mainMode; // 0 = sun, 1 = snow, 2 = humidity/drop, 3 = fan
    private int temperature;
    private int airPower; // probably 0 to 4
    private int tilt; // probably 0 to 3, or more
    private boolean sleepMode;
    private boolean silentMode;
    private int timer;
    private boolean power;

    public AirCon(String name, int mainMode, int temperature, int airPower, int tilt, boolean sleepMode, boolean silentMode, int timer, boolean power) {
        this.name = name;
        this.mainMode = mainMode;
        this.temperature = temperature;
        this.airPower = airPower;
        this.tilt = tilt;
        this.sleepMode = sleepMode;
        this.silentMode = silentMode;
        this.timer = timer;
        this.power = power;
    }

    public String getName()
    {
        return name;
    }

    public int getMainMode() {
        return mainMode;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getAirPower() {
        return airPower;
    }

    public int getTilt() {
        return tilt;
    }

    public boolean isSleepMode() {
        return sleepMode;
    }

    public boolean isSilentMode() {
        return silentMode;
    }

    public int getTimer() {
        return timer;
    }

    public boolean isPower() {
        return power;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMainMode(int mainMode) {
        this.mainMode = mainMode;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setAirPower(int airPower) {
        this.airPower = airPower;
    }

    public void setTilt(int tilt) {
        this.tilt = tilt;
    }

    public void setSleepMode(boolean sleepMode) {
        this.sleepMode = sleepMode;
    }

    public void setSilentMode(boolean silentMode) {
        this.silentMode = silentMode;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setPower(boolean power) {
        this.power = power;
    }
}