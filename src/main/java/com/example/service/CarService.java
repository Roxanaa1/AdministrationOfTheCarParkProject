package com.example.service;
import com.example.model.Car;
import com.example.model.CarFiltres;
import com.example.model.User;
import com.example.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CarService
{
    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public Car addCar(Car car, User user)
    {
        car.setRegistrationNumber(user.getId().toString());
        //am convertit la String ca id de la user e Long
        return carRepository.save(car);
    }
    public Car updateCar(String registrationNumber, Car carDetails)
    {
        return carRepository.findById(registrationNumber).map(car -> {
            car.setBrand(carDetails.getBrand());
            car.setColor(carDetails.getColor());
            car.setFuelType(carDetails.getFuelType());
            return carRepository.save(car);
        }).orElseThrow(() -> new EntityNotFoundException("Car not found with registration number: " + registrationNumber));
    }
    public void deleteCar(String registrationNumber) {
        carRepository.deleteById(registrationNumber);
    }
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }
    public Optional<Car> getCarByRegistration(String registrationNumber)
    {
        return carRepository.findById(registrationNumber);
    }
    public List<Car> findCarsByFilters(CarFiltres filters)
    {
        Specification<Car> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getRegistrationNumber() != null && !filters.getRegistrationNumber().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("registrationNumber"), filters.getRegistrationNumber()));
            }
            if (filters.getBrand() != null && !filters.getBrand().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("brand"), filters.getBrand()));
            }
            if (filters.getModel() != null && !filters.getModel().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("model"), filters.getModel()));
            }
            if (filters.getColor() != null && !filters.getColor().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("color"), filters.getColor()));
            }
            if (filters.getYearOfFabrication() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("yearOfFabrication"), filters.getYearOfFabrication()));
            }
            if (filters.getEngineCapacity() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("engineCapacity"), filters.getEngineCapacity()));
            }
            if (filters.getFuelType() != null && !filters.getFuelType().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("fuelType"), filters.getFuelType()));
            }
            if (filters.getPower() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("power"), filters.getPower()));
            }
            if (filters.getTorque() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("torque"), filters.getTorque()));
            }
            if (filters.getTrunkVolume() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("trunkVolume"), filters.getTrunkVolume()));
            }
            if (filters.getPrice() != 0.0) {
                predicates.add(criteriaBuilder.equal(root.get("price"), filters.getPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return carRepository.findAll(spec);
    }
}
