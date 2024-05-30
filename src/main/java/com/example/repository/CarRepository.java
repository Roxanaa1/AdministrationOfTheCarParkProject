package com.example.repository;

import com.example.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,String>,JpaSpecificationExecutor<Car>
{
    Optional<Car>findById(String id);
    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);
    List<Car> findByYearOfFabrication(int yearOfFabrication);
    List<Car> findByBrandAndColorAndFuelType(String brand, String color, String fuelType);
}
