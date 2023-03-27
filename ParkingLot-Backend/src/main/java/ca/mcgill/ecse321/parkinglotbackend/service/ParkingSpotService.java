package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.TicketRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpotService {
    @Autowired
    ParkingSpotRepository parkingSpotRepository;


    @Transactional
    public ParkingSpot createParkingSpot(int floor, int number) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setNumber(number);
        parkingSpot.setFloor(floor);
        return parkingSpot;
    }

    @Transactional
    public ParkingSpot updateParkingSpot(long parkingSpotID, int newFloor, int newNumber) {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(parkingSpotID);
        parkingSpot.setFloor(newFloor);
        parkingSpot.setNumber(newNumber);
        return parkingSpot;
    }
    @Transactional
    public ParkingSpot deleteParkingSpot(long ParkingSpotID) throws Exception {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(ParkingSpotID);
        if (parkingSpot == null) {
            throw new Exception("There is no Parking Spot in the system with this ID.");
        }
        parkingSpotRepository.delete(parkingSpot);
        return parkingSpot;
    }
    @Transactional
    public ParkingSpot findParkingSpotByID(long ParkingSpotID) throws Exception {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(ParkingSpotID);
        if (parkingSpot == null) {
            throw new Exception("There is no Parking Spot in the system with this ID.");
        }
        return parkingSpot;
    }
    @Transactional
    public MonthlyReservation findMonthlyReservation(long ParkingSpotID) throws Exception {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(ParkingSpotID);
        if (parkingSpot == null) {
            throw new Exception("There is no Parking Spot in the system with this ID.");
        }
        MonthlyReservation monthlyReservation = parkingSpot.getMonthlyReservation();

        return monthlyReservation;
    }

    @Transactional
    public List<ParkingSpot> findAllParkingSpots() {
        return toList(parkingSpotRepository.findAll());
    }


    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}

