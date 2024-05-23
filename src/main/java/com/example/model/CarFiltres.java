package com.example.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class CarFiltres
{
    private String registrationNumber;

    private String brand;

    private String model;

    private String color;

    private int yearOfFabrication;

    private int engineCapacity;

    private String fuelType;
    private int power;

    private int torque;
    private int trunkVolume;

    private double price;
}
