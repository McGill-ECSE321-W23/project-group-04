package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    public ParkingSpot getParkingSpot(Long spotId) {
        if (spotId != null) {
            return parkingSpotRepository.findParkingSpotByParkingSpotID(spotId);
        }
        throw new IllegalArgumentException("id is null");
    }

    public ParkingSpot getParkingSpotByReservationId(Long reservationId) {
        if (reservationId != null) {
            return parkingSpotRepository.findParkingSpotByMonthlyReservation_MonthlyReservationID(reservationId);
        }
        throw new IllegalArgumentException("id is null");
    }

    public MonthlyReservation unbind(Long spotId) {
        ParkingSpot parkingSpot = getParkingSpot(spotId);
        MonthlyReservation monthlyReservation = parkingSpot.getMonthlyReservation();
        parkingSpot.setMonthlyReservation(null);
        parkingSpotRepository.save(parkingSpot);
        return monthlyReservation;
    }

    public ParkingSpot attachReservation(Long spotId, MonthlyReservation monthlyReservation) throws Exception {
        ParkingSpot parkingSpot = getParkingSpot(spotId);

        if (parkingSpot.getMonthlyReservation() != null) {
            throw new Exception("Reservation already exists on spot");
        }

        parkingSpot.setMonthlyReservation(monthlyReservation);
        return parkingSpotRepository.save(parkingSpot);
    }
}
