package com.example.service;

import com.example.model.Car;
import com.example.model.User;
import com.example.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService
{
    @Autowired
    private CarRepository carRepository;

    public Car addCar(Car car, User user)
    {
        car.setRegistrationNumber( user.getId().toString());//am convertit la string deoarece userId este long
        return carRepository.save(car);
    }

    public Car updateCar(String registrationNumber, Car carDetails) {
        return carRepository.findById(registrationNumber).map(car -> {
            // Actualizează aici proprietățile pe care dorești să le schimbi
            car.setMake(carDetails.getMake());
            car.setModel(carDetails.getModel());
            car.setColor(carDetails.getColor());
            car.setYearOfFabrication(carDetails.getYearOfFabrication());
            car.setEngineCapacity(carDetails.getEngineCapacity());
            car.setFuelType(carDetails.getFuelType());
            car.setPower(carDetails.getPower());
            car.setTorque(carDetails.getTorque());
            car.setTrunkVolume(carDetails.getTrunkVolume());
            car.setPrice(carDetails.getPrice());
            // Alte setări după necesități
            return carRepository.save(car);
        }).orElseThrow(() -> new EntityNotFoundException("Car not found with registration number: " + registrationNumber));
    }


    public void deleteCar(String nrInmatriculare) {
        carRepository.deleteById(nrInmatriculare);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }
    public Optional<Car> getCarByRegistration(String nrInmatriculare) {
        return carRepository.findById(nrInmatriculare);
    }
}
