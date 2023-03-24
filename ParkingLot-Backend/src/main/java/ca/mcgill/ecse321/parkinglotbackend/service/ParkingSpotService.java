package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.TicketRepository;
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
    public ParkingSpot updateParkingSpot(long ParkingSpotID, int floor, int number) {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(ParkingSpotID);
        parkingSpot.setFloor(floor);
        parkingSpot.setNumber(number);
        return parkingSpot;
    }
    @Transactional
    public ParkingSpot deleteParkingSpot(long ParkingSpotID) {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(ParkingSpotID);
        parkingSpotRepository.delete(parkingSpot);
        return parkingSpot;
    }
    @Transactional
    public ParkingSpot findParkingSpotByID(long ParkingSpotID) {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(ParkingSpotID);
        return parkingSpot;
    }

    @Transactional
    public List<ParkingSpot> getAllTickets() {
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


