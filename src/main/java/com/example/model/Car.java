package com.example.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "masini")
public class Car
{
    @Id
    @Column(name = "nr_inmatriculare")
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "id_utilizator")
    private User user;

    @Column(name = "marca")
    private String make;

    @Column(name = "modelul")
    private String model;

    @Column(name = "culoarea")
    private String color;

    @Column(name = "anul_fabricatiei")
    private int yearOfFabrication;

    @Column(name = "capacitatea_cilindrica")
    private int engineCapacity;

    @Column(name = "tipul_de_combustibil")
    private String fuelType;

    @Column(name = "puterea")
    private int power;

    @Column(name = "cuplul")
    private int torque;

    @Column(name = "volumul_portbagajului")
    private int trunkVolume;

    @Column(name = "pretul")
    private double price;
}

