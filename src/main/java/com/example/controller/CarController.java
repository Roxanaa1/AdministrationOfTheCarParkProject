package com.example.controller;

import com.example.model.Car;
import com.example.model.CarFiltres;
import com.example.model.User;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/jpa/cars")
public class CarController
{
    @Autowired
    private CarService carService;
    CarController(CarService carService)
    {
        this.carService=carService;
    }
    @GetMapping("/getAllCars")
    public ResponseEntity<List<Car>> getAllCars()
    {
        List<Car> cars = carService.findAllCars();
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/getCarByRegistration{registrationNumber}")
    public ResponseEntity<Car> getCarByRegistration(@PathVariable String registrationNumber)
    {
        Car car = carService.getCarByRegistration(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Car not found with registration number: " + registrationNumber));
        return ResponseEntity.ok(car);
    }
    @PostMapping("/createCar")
    public ResponseEntity<Car> createCar(@RequestBody Car car , User user)
    {
        Car newCar = carService.addCar(car,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
    }
    @PutMapping("/updateCar{registrationNumber}")
    public ResponseEntity<Car> updateCar(@PathVariable String registrationNumber, @RequestBody Car carDetails)
    {
        Car updatedCar = carService.updateCar(registrationNumber, carDetails);
        return ResponseEntity.ok(updatedCar);
    }
    @DeleteMapping("/deleteCar{registrationNumber}")
    public ResponseEntity<Void> deleteCar(@PathVariable String registrationNumber)
    {
        carService.deleteCar(registrationNumber);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/filtered")
    public List<Car> getFilteredCars(@RequestBody CarFiltres filters)
    {
        return carService.findCarsByFilters(filters);
    }

}
