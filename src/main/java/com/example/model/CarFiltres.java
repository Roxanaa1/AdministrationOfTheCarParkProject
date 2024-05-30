package com.example.model;

public class CarFiltres {
    private String brand;
    private String color;
    private String fuelType;
    public CarFiltres(){}
    // Constructor
    public CarFiltres(String brand, String color, String fuelType) {
        this.brand = brand;
        this.color = color;
        this.fuelType = fuelType;
    }

    // Getters and setters

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
