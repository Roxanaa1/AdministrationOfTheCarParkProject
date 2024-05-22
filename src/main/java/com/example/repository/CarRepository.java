package com.example.repository;

import com.example.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,String>
{
    Optional<Car>findById(String id);
    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);
    List<Car> findByYearOfFabrication(int yearOfFabrication);

}
