package ca.mcgill.ecse321.parkinglotbackend.dao;

import java.util.List;

import ca.mcgill.ecse321.parkinglotbackend.model.Car;

import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository<Car, String>{
    
    boolean existsCarByCarID(Long carID);
    Car findCarByCarID(Long carID);

    boolean existsCarByLicensePlate(String licensePlate);
    Car findCarByLicensePlate(String licensePlate);

    boolean existsCarByOwner_PersonID(Long ownerID);
    List<Car> findCarByOwner_PersonID(Long ownerID);
}