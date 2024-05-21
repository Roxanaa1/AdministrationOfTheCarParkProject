package com.example.controller;

import com.example.model.Car;
import com.example.model.User;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping("/")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.findAllCars();
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/{registrationNumber}")
    public ResponseEntity<Car> getCarByRegistration(@PathVariable String registrationNumber) {
        Car car = carService.getCarByRegistration(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Car not found with registration number: " + registrationNumber));
        return ResponseEntity.ok(car);
    }
    @PostMapping("/")
    public ResponseEntity<Car> createCar(@RequestBody Car car , User user) {
        Car newCar = carService.addCar(car,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
    }
    @PutMapping("/{registrationNumber}")
    public ResponseEntity<Car> updateCar(@PathVariable String registrationNumber, @RequestBody Car carDetails) {
        Car updatedCar = carService.updateCar(registrationNumber, carDetails);
        return ResponseEntity.ok(updatedCar);
    }
    @DeleteMapping("/{registrationNumber}")
    public ResponseEntity<Void> deleteCar(@PathVariable String registrationNumber) {
        carService.deleteCar(registrationNumber);
        return ResponseEntity.ok().build();
    }








}
