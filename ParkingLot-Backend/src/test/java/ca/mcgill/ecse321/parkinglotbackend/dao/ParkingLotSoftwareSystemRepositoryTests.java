package ca.mcgill.ecse321.parkinglotbackend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingLotSoftwareSystemRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;

@SpringBootTest
public class ParkingLotSoftwareSystemRepositoryTests {
    @Autowired
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;

    // clear database after testing
    @AfterEach
    public void clearDatabase() {
        parkingLotSoftwareSystemRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadParkingLotSoftwareSystem() {
        // Create object
        String parkingLotSoftwareSystemID = "1";
        float monthlyFee = 12;
        float feePer15m = 5;
        int maxStay = 20;
        int numberOfRegularParkingSpots = 300;
        int numberOfLargeParkingSpots = 50;
        int numberOfMonthlyFloors = 3;
        int numberOfMonthlySpotsPerFloor = 100;
        int numberOfGarages = 4;
        ParkingLotSoftwareSystem parkingSystem = new ParkingLotSoftwareSystem();
        parkingSystem.setParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);
        parkingSystem.setMonthlyFee(monthlyFee);
        parkingSystem.setFeePer15m(feePer15m);
        parkingSystem.setMaxStay(maxStay);
        parkingSystem.setNumberOfRegularParkingSpots(numberOfRegularParkingSpots);
        parkingSystem.setNumberOfLargeParkingSpots(numberOfLargeParkingSpots);
        parkingSystem.setNumberOfMonthlyFloors(numberOfMonthlyFloors);
        parkingSystem.setNumberOfMonthlySpotsPerFloor(numberOfMonthlySpotsPerFloor);
        parkingSystem.setNumberOfGarages(numberOfGarages);

        // Save object to database
        parkingLotSoftwareSystemRepository.save(parkingSystem);

        // Read object from database
        parkingSystem = null;
        parkingSystem = parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(parkingLotSoftwareSystemID);

        // Assert object has correct values
        assertNotNull(parkingSystem);
        assertEquals(parkingLotSoftwareSystemID, parkingSystem.getParkingLotSoftwareSystemID());
        assertEquals(monthlyFee, parkingSystem.getMonthlyFee());
        assertEquals(feePer15m, parkingSystem.getFeePer15m());
        assertEquals(maxStay, parkingSystem.getMaxStay());
        assertEquals(numberOfRegularParkingSpots, parkingSystem.getNumberOfRegularParkingSpots());
        assertEquals(numberOfLargeParkingSpots, parkingSystem.getNumberOfLargeParkingSpots());
        assertEquals(numberOfMonthlyFloors, parkingSystem.getNumberOfMonthlyFloors());
        assertEquals(numberOfMonthlySpotsPerFloor, parkingSystem.getNumberOfMonthlySpotsPerFloor());
        assertEquals(numberOfGarages, parkingSystem.getNumberOfGarages());
    }
}
