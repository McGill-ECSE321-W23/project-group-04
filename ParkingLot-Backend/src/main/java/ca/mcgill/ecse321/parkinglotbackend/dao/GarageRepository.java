package ca.mcgill.ecse321.parkinglotbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;


public interface GarageRepository extends CrudRepository<Garage, String>{
	Garage findGarageByGarageID(Long garageId);
}