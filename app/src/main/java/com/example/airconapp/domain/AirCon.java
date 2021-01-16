package com.example.airconapp.domain;

import java.io.Serializable;
import java.util.Random;

public class AirCon implements Serializable
{
    private String name;
    private int mainMode; // 0 = sun, 1 = snow, 2 = auto, 3 = humidity, 4 = fan
    private int temperature;
    private int airIntensity; // probably 0 to 4
    private int tilt; // probably 0 to 3, or more
    private boolean sleepMode;
    private boolean silentMode;
    private int timer;
    private int timerOff;
    private boolean power = false;
    private AirConDetails airConDetails;
    private Random random = new Random();
    private int randomSerial;

    public AirCon(String name, int mainMode, int temperature, int airIntensity, int tilt, boolean sleepMode, boolean silentMode, int timer, int timerOff, boolean power) {
        this.name = name;
        this.mainMode = mainMode;
        this.temperature = temperature;
        this.airIntensity = airIntensity;
        this.tilt = tilt;
        this.sleepMode = sleepMode;
        this.silentMode = silentMode;
        this.timer = timer;
        this.timerOff = timerOff;
        this.power = power;
        randomSerial = random.nextInt(3265) + 1000;
        this.airConDetails = new AirConDetails(name, randomSerial, 23884, 25249," A++", 58, "1078x325x246", "955x700x396" );
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

    public int getAirIntensity() {
        return airIntensity;
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

    public int getTimerOff() {
        return timerOff;
    }

    public boolean isPower() {
        return power;
    }

    public AirConDetails getAirConDetails() {
        return airConDetails;
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

    public void setAirIntensity(int airIntensity) {
        this.airIntensity = airIntensity;
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

    public void setTimerOff(int timerOff) {
        this.timerOff = timerOff;
    }

    public void setPower(boolean power) {
        this.power = power;
    }
}