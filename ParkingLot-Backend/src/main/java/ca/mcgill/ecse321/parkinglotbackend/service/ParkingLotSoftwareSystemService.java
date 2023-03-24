package ca.mcgill.ecse321.parkinglotbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingLotSoftwareSystemRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import jakarta.transaction.Transactional;

@Service
public class ParkingLotSoftwareSystemService {
    @Autowired
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;

    @Transactional
    public ParkingLotSoftwareSystem createParkingLotSoftwareSystem(float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages) {
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

    @Transactional
    public ParkingLotSoftwareSystem getParkingLotSoftwareSystem(long parkingLotSoftwareSystemID) {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);
        return system;
    }

    @Transactional
    public ParkingLotSoftwareSystem updateParkingLotSoftwareSystem(long parkingLotSoftwareSystemID, float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages) throws Exception {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);
        if (system == null) {
            throw new Exception("No system with this id exists!");
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

    @Transactional
    public ParkingLotSoftwareSystem deleteParkingLotSoftwareSystem(long parkingLotSoftwareSystemID) throws Exception {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);
        if (system == null) {
            throw new Exception("No system with this id exists!");
        }
        parkingLotSoftwareSystemRepository.delete(system);
        return system;
    }
}
