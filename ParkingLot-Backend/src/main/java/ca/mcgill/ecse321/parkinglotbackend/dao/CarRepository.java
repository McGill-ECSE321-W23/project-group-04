package ca.mcgill.ecse321.parkinglotbackend.dao;

import java.util.List;

import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository<Car, String>{

    Car findCarByCarID(Long carID);
    Car findCarByCarLicensePlate(String licensePlate);

    List<Car> findCarByOwnerID(Long ownerID);
}