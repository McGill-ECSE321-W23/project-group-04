package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import org.springframework.data.repository.CrudRepository;


public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, String> {
    ParkingSpot findParkingSpotByParkingSpotID(Long parkingSpotID);

    ParkingSpot findParkingSpotByMonthlyReservation_MonthlyReservationID(Long monthlyReservationId);
}