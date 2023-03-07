package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ParkingSpotRepositoryTests {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    // clear database after testing
    @AfterEach
    public void clearDatabase() {
        parkingSpotRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadParkingSpot() {
        // Create parking spot
        int floor = 1;
        int number = 1;
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setFloor(floor);
        parkingSpot.setNumber(number);

        // Save object
        parkingSpot = parkingSpotRepository.save(parkingSpot);
        Long id = parkingSpot.getParkingSpotID();

        // Read object from database
        parkingSpot = parkingSpotRepository.findParkingSpotByParkingSpotID(id);

        // Assert that object has correct attributes
        assertNotNull(parkingSpot);
        assertEquals(id, parkingSpot.getParkingSpotID());
        assertEquals(floor, parkingSpot.getFloor());
        assertEquals(number, parkingSpot.getNumber());
    }

}
