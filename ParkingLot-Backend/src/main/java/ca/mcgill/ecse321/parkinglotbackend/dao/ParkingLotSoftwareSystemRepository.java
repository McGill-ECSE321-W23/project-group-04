package ca.mcgill.ecse321.parkinglotbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;

public interface ParkingLotSoftwareSystemRepository extends CrudRepository<ParkingLotSoftwareSystem, Long>{
	ParkingLotSoftwareSystem findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(Long parkingLotSoftwareSystemID);
}