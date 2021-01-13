package com.example.airconapp.domain;

public class AirConDetails
{
    private String name;
    private int serialNo;
    private int coolingPower;
    private int heatingPower;
    private String energyClass;
    private int noisePower;
    private String interiorUnitDimensions;
    private String exteriorUnitDimensions;

    public AirConDetails(String name, int serialNo, int coolingPower, int heatingPower, String energyClass, int noisePower, String interiorUnitDimensions, String exteriorUnitDimensions) {
        this.name = name;
        this.serialNo = serialNo;
        this.coolingPower = coolingPower;
        this.heatingPower = heatingPower;
        this.energyClass = energyClass;
        this.noisePower = noisePower;
        this.interiorUnitDimensions = interiorUnitDimensions;
        this.exteriorUnitDimensions = exteriorUnitDimensions;
    }

    public String getName() {
        return name;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public int getCoolingPower() {
        return coolingPower;
    }

    public int getHeatingPower() {
        return heatingPower;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public int getNoisePower() {
        return noisePower;
    }

    public String getInteriorUnitDimensions() {
        return interiorUnitDimensions;
    }

    public String getExteriorUnitDimensions() {
        return exteriorUnitDimensions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public void setCoolingPower(int coolingPower) {
        this.coolingPower = coolingPower;
    }

    public void setHeatingPower(int heatingPower) {
        this.heatingPower = heatingPower;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    public void setNoisePower(int noisePower) {
        this.noisePower = noisePower;
    }

    public void setInteriorUnitDimensions(String interiorUnitDimensions) {
        this.interiorUnitDimensions = interiorUnitDimensions;
    }

    public void setExteriorUnitDimensions(String exteriorUnitDimensions) {
        this.exteriorUnitDimensions = exteriorUnitDimensions;
    }
}