package com.example.service;
import com.example.model.Car;
import com.example.model.CarFiltres;
import com.example.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class CarService
{
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car)
    {
        return carRepository.save(car);
    }
    public Car updateCar(String registrationNumber, Car carDetails)
    {
        return carRepository.findById(registrationNumber).map(car -> {
            if (carDetails.getBrand() != null) {
                car.setBrand(carDetails.getBrand());
            }
            if (carDetails.getModel() != null) {
                car.setModel(carDetails.getModel());
            }
            if (carDetails.getColor() != null) {
                car.setColor(carDetails.getColor());
            }
            if (carDetails.getYearOfFabrication() != 0) {
                car.setYearOfFabrication(carDetails.getYearOfFabrication());
            }
            if (carDetails.getEngineCapacity() != 0) {
                car.setEngineCapacity(carDetails.getEngineCapacity());
            }
            if (carDetails.getFuelType() != null) {
                car.setFuelType(carDetails.getFuelType());
            }
            if (carDetails.getPower() != 0) {
                car.setPower(carDetails.getPower());
            }
            if (carDetails.getTorque() != 0) {
                car.setTorque(carDetails.getTorque());
            }
            if (carDetails.getTrunkVolume() != 0) {
                car.setTrunkVolume(carDetails.getTrunkVolume());
            }
            if (carDetails.getPrice() != 0) {
                car.setPrice(carDetails.getPrice());
            }
            return carRepository.save(car);
        }).orElseThrow(() -> new EntityNotFoundException("Car not found with registration number: " + registrationNumber));
    }

    public void deleteCar(String registrationNumber)
    {
        if (carRepository.existsById(registrationNumber))
        {
            carRepository.deleteById(registrationNumber);
        } else {
            throw new RuntimeException("Car not found with registration number: " + registrationNumber);
        }
    }
    public List<Car> findCarsByFilters(CarFiltres filters)
    {
        Specification<Car> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getBrand() != null && !filters.getBrand().isEmpty())
            {
                predicates.add(criteriaBuilder.equal(root.get("brand"), filters.getBrand()));
            }
            if (filters.getColor() != null && !filters.getColor().isEmpty())
            {
                predicates.add(criteriaBuilder.equal(root.get("color"), filters.getColor()));
            }
            if (filters.getFuelType() != null && !filters.getFuelType().isEmpty())
            {
                predicates.add(criteriaBuilder.equal(root.get("fuelType"), filters.getFuelType()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return carRepository.findAll(spec);
    }
}
