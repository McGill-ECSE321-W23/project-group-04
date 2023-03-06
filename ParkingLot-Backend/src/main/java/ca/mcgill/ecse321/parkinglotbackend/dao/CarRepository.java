package ca.mcgill.ecse321.parkinglotbackend.dao;

import java.util.List;

import ca.mcgill.ecse321.parkinglotbackend.model.Car;

import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository<Car, String>{

    Car findCarByCarID(Long carID);
    Car findCarByLicensePlate(String licensePlate);

    List<Car> findCarByOwner_PersonID(Long ownerID);
}