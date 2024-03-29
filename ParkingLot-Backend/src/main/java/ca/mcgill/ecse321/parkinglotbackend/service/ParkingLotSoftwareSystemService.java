package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingLotSoftwareSystemRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qin Xuan Xu
 * using template from tutorials
 */
@Service
public class ParkingLotSoftwareSystemService {
    @Autowired
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;

    /**
     * Create a new parking lot software system
     * 
     * @param aMonthlyFee - monthly fee
     * @param aFeePer15m - fee per 15 minutes
     * @param aMaxStay - maximum stay
     * @param aNumberOfRegularParkingSpots - number of regular parking spots
     * @param aNumberOfLargeParkingSpots - number of large parking spots
     * @param aNumberOfMonthlyFloors - number of monthly floors
     * @param aNumberOfMonthlySpotsPerFloor - number of monthly spots per floor
     * @param aNumberOfGarages - number of garages
     * @return the created parking lot software system
     * @throws Exception if any of the values are negative
     * @author Qin Xuan Xu
     */
    @Transactional
    public ParkingLotSoftwareSystem createParkingLotSoftwareSystem(float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages) throws Exception {
        if (aMonthlyFee < 0 || aFeePer15m < 0 || aMaxStay < 0 || aNumberOfRegularParkingSpots < 0 || aNumberOfLargeParkingSpots < 0 || aNumberOfMonthlyFloors < 0 || aNumberOfMonthlySpotsPerFloor < 0 || aNumberOfGarages < 0) {
            throw new Exception("Values cannot be negative!");
        }
        ParkingLotSoftwareSystem system = new ParkingLotSoftwareSystem();
        system.setMonthlyFee(aMonthlyFee);
        system.setFeePer15m(aFeePer15m);
        system.setMaxStay(aMaxStay);
        system.setNumberOfRegularParkingSpots(aNumberOfRegularParkingSpots);
        system.setNumberOfLargeParkingSpots(aNumberOfLargeParkingSpots);
        system.setNumberOfMonthlyFloors(aNumberOfMonthlyFloors);
        system.setNumberOfMonthlySpotsPerFloor(aNumberOfMonthlySpotsPerFloor);
        system.setNumberOfGarages(aNumberOfGarages);
        parkingLotSoftwareSystemRepository.save(system);
        return system;
    }

    /**
     * Get a parking lot software system
     * 
     * @param parkingLotSoftwareSystemID - id of the system
     * @return the parking lot software system
     * @throws Exception if no system with this id exists
     * @author Qin Xuan Xu
     */
    @Transactional
    public ParkingLotSoftwareSystem getParkingLotSoftwareSystem(long parkingLotSoftwareSystemID) throws Exception {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);
        if (system == null) {
            throw new Exception("No system with this id exists!");
        }
        return system;
    }

    /**
     * Get first parking lot software system in database
     *
     * @return the parking lot software system
     * @throws Exception if no system with this id exists
     * @author Qin Xuan Xu
     */
    @Transactional
    public ParkingLotSoftwareSystem getParkingLotSoftwareSystem() throws Exception {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemRepository.getParkingLotSoftwareSystemByParkingLotSoftwareSystemIDNotNull();
        if (system == null) {
            throw new Exception("No system with this id exists!");
        }
        return system;
    }

    /**
     * Get all parking lot software system in database
     *
     * @return all parking lot software system in database
     * @author Qin Xuan Xu
     */
    @Transactional
    public List<ParkingLotSoftwareSystem> getAllParkingLotSoftwareSystem() throws Exception {
        return toList(parkingLotSoftwareSystemRepository.findAll());
    }

    /**
     * Update a parking lot software system
     * 
     * @param parkingLotSoftwareSystemID - id of the system
     * @param aMonthlyFee - monthly fee
     * @param aFeePer15m - fee per 15 minutes
     * @param aMaxStay - maximum stay
     * @param aNumberOfRegularParkingSpots - number of regular parking spots
     * @param aNumberOfLargeParkingSpots - number of large parking spots
     * @param aNumberOfMonthlyFloors - number of monthly floors
     * @param aNumberOfMonthlySpotsPerFloor - number of monthly spots per floor
     * @param aNumberOfGarages - number of garages
     * @return the updated parking lot software system
     * @throws Exception if any of the values are negative
     * @author Qin Xuan Xu
     */
    @Transactional
    public ParkingLotSoftwareSystem updateParkingLotSoftwareSystem(long parkingLotSoftwareSystemID, float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages) throws Exception {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);
        if (system == null) {
            throw new Exception("No system with this id exists!");
        }
        if (aMonthlyFee < 0 || aFeePer15m < 0 || aMaxStay < 0 || aNumberOfRegularParkingSpots < 0 || aNumberOfLargeParkingSpots < 0 || aNumberOfMonthlyFloors < 0 || aNumberOfMonthlySpotsPerFloor < 0 || aNumberOfGarages < 0) {
            throw new Exception("Values cannot be negative!");
        }
        system.setMonthlyFee(aMonthlyFee);
        system.setFeePer15m(aFeePer15m);
        system.setMaxStay(aMaxStay);
        system.setNumberOfRegularParkingSpots(aNumberOfRegularParkingSpots);
        system.setNumberOfLargeParkingSpots(aNumberOfLargeParkingSpots);
        system.setNumberOfMonthlyFloors(aNumberOfMonthlyFloors);
        system.setNumberOfMonthlySpotsPerFloor(aNumberOfMonthlySpotsPerFloor);
        system.setNumberOfGarages(aNumberOfGarages);
        parkingLotSoftwareSystemRepository.save(system);
        return system;
    }

    /**
     * Delete a parking lot software system
     * 
     * @param parkingLotSoftwareSystemID - id of the system
     * @return the deleted parking lot software system
     * @throws Exception if no system with this id exists
     * @author Qin Xuan Xu
     */
    @Transactional
    public ParkingLotSoftwareSystem deleteParkingLotSoftwareSystem(long parkingLotSoftwareSystemID) throws Exception {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);
        if (system == null) {
            throw new Exception("No system with this id exists!");
        }
        parkingLotSoftwareSystemRepository.delete(system);
        return system;
    }

    /**
     * helper method to convert to list of objects
     * @param iterable
     * @param <T>
     * @return list of objects
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
