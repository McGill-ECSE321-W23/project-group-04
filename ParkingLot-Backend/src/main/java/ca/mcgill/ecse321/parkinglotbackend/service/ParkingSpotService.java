package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpotService {
    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    /**
     * create a parking spot in the system
     * @param floor parking spot floor
     * @param number  parking spot number
     * @return the new Parking Spot
     * @author faizachowdhury
     */
    @Transactional
    public ParkingSpot createParkingSpot(int floor, int number) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setNumber(number);
        parkingSpot.setFloor(floor);
        return parkingSpot;
    }

    /**
     * update a parking spot already in the system
     * @param parkingSpotID ID of the spot
     * @param newFloor  new parking spot floor
     * @param newNumber new parking spot number
     * @return the updated Parking Spot
     * @author faizachowdhury
     */
    @Transactional
    public ParkingSpot updateParkingSpot(long parkingSpotID, int newFloor, int newNumber) throws Exception {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(parkingSpotID);
        if (parkingSpot == null) {
            throw new Exception("There is no Parking Spot in the system with this ID.");
        }
        parkingSpot.setFloor(newFloor);
        parkingSpot.setNumber(newNumber);
        return parkingSpot;
    }
    /**
     * delete a parking spot from the system
     * @param parkingSpotID ID of the parking spot
     * @return the deleted Parking Spot
     * @author faizachowdhury
     */
    @Transactional
    public ParkingSpot deleteParkingSpot(long parkingSpotID) throws Exception {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(parkingSpotID);
        if (parkingSpot == null) {
            throw new Exception("There is no Parking Spot in the system with this ID.");
        }
        parkingSpotRepository.delete(parkingSpot);
        return parkingSpot;
    }
    /**
     * find a parking spot in the system
     * @param parkingSpotID id of the parking spot
     * @return the required Parking Spot
     * @author faizachowdhury
     */
    @Transactional
    public ParkingSpot findParkingSpotByID(long parkingSpotID) throws Exception {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(parkingSpotID);
        if (parkingSpot == null) {
            throw new Exception("There is no Parking Spot in the system with this ID.");
        }
        return parkingSpot;
    }
    /**
     * find monthly reservation of a parking spot if it exists
     * @param parkingSpotID id of the parking spot
     * @return the monthly reservation
     * @author faizachowdhury
     */
    @Transactional
    public MonthlyReservation findMonthlyReservation(long parkingSpotID) throws Exception {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(parkingSpotID);
        if (parkingSpot == null) {
            throw new Exception("There is no Parking Spot in the system with this ID.");
        }
        if (!parkingSpot.hasMonthlyReservation()) {
            throw new Exception("There is no monthly reservation for this Parking Spot.");
        }
        MonthlyReservation monthlyReservation = parkingSpot.getMonthlyReservation();
        return monthlyReservation;
    }

    /**
     * find a list of all the parking spots in the system
     * @return the required list
     * @author faizachowdhury
     */
    @Transactional
    public List<ParkingSpot> findAllParkingSpots() {
        return toList(parkingSpotRepository.findAll());
    }

    public MonthlyReservation unbind(Long spotId) throws Exception {
        ParkingSpot parkingSpot = findParkingSpotByID(spotId);
        MonthlyReservation monthlyReservation = parkingSpot.getMonthlyReservation();
        parkingSpot.setMonthlyReservation(null);
        parkingSpotRepository.save(parkingSpot);
        return monthlyReservation;
    }

    public ParkingSpot attachReservation(Long spotId, MonthlyReservation monthlyReservation) throws Exception {
        ParkingSpot parkingSpot = findParkingSpotByID(spotId);

        if (parkingSpot.getMonthlyReservation() != null) {
            throw new Exception("Reservation already exists on spot");
        }

        parkingSpot.setMonthlyReservation(monthlyReservation);
        return parkingSpotRepository.save(parkingSpot);
    }

    public ParkingSpot getParkingSpotByReservationId(Long reservationId) {
        if (reservationId != null) {
            return parkingSpotRepository.findParkingSpotByMonthlyReservation_MonthlyReservationID(reservationId);
        }
        throw new IllegalArgumentException("id is null");
    }

    /**
     * code taken from tutorial notes
     * @param iterable
     * @param <T>
     * @return list of iterable
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
