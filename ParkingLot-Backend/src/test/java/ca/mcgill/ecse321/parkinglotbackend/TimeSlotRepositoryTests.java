package ca.mcgill.ecse321.parkinglotbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.parkinglotbackend.dao.TimeSlotRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingLotSoftwareSystemRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;

@SpringBootTest
public class TimeSlotRepositoryTests {
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;

    @AfterEach
	public void clearDatabase() {
		timeSlotRepository.deleteAll();
        parkingLotSoftwareSystemRepository.deleteAll();
	}

    @Test
    public void testPersistAndLoadTimeSlot() {
        // Create system object for timeslot
        String parkingLotSoftwareSystemID = "5";
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
        // Save system to database
        parkingLotSoftwareSystemRepository.save(parkingSystem);

        // Create timeslot
        Long timeSlotID = (long) 1;
        DayOfWeek dayOfTheWeek = DayOfWeek.MONDAY;
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(4, 30);
        TimeSlot mondaySlot = new TimeSlot();
        mondaySlot.setTimeSlotID(timeSlotID);
        mondaySlot.setDayOfTheWeek(dayOfTheWeek);
        mondaySlot.setStartTime(startTime);
        mondaySlot.setEndTime(endTime);
        mondaySlot.setParkingLotSoftwareSystem(parkingSystem);
        // Save timeslot to database
        timeSlotRepository.save(mondaySlot);

        // Read object and assert attributes
        mondaySlot = null;
        parkingSystem = null;
        mondaySlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        assertNotNull(mondaySlot);
        assertEquals(timeSlotID, mondaySlot.getTimeSlotID());
        assertEquals(dayOfTheWeek, mondaySlot.getDayOfTheWeek());
        assertEquals(startTime, mondaySlot.getStartTime());
        assertEquals(endTime, mondaySlot.getEndTime());

        assertNotNull(mondaySlot.getParkingLotSoftwareSystem());
        assertEquals(parkingLotSoftwareSystemID, mondaySlot.getParkingLotSoftwareSystem().getParkingLotSoftwareSystemID());
    }
}
