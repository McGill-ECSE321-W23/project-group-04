package ca.mcgill.ecse321.parkinglotbackend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

@SpringBootTest
public class TimeSlotRepositoryTests {
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private StaffAccountRepository staffAccountRepository;

    // clear database after testing
    @AfterEach
	public void clearDatabase() {
		timeSlotRepository.deleteAll();
        parkingLotSoftwareSystemRepository.deleteAll();
        staffAccountRepository.deleteAll();
        personRepository.deleteAll();
	}

    @Test
    public void testPersistAndLoadTimeSlot() {
        // Create system object
        float monthlyFee = 12;
        float feePer15m = 5;
        int maxStay = 20;
        int numberOfRegularParkingSpots = 300;
        int numberOfLargeParkingSpots = 50;
        int numberOfMonthlyFloors = 3;
        int numberOfMonthlySpotsPerFloor = 100;
        int numberOfGarages = 4;
        ParkingLotSoftwareSystem parkingSystem = new ParkingLotSoftwareSystem();
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
        Long parkingLotSoftwareSystemID = parkingSystem.getParkingLotSoftwareSystemID();

        // Create staff acount + person
        String phoneNumber = "32103217";
        String name = "Bob";
        Person person = new Person();
        person.setPhoneNumber(phoneNumber);
        person.setName(name);
        String email = "whats@ligma.com";
        String password = "gottem";
        float salary = 11.1f;
        StaffAccount staffAccount = new StaffAccount();
        staffAccount.setEmail(email);
        staffAccount.setPassword(password);
        staffAccount.setSalary(salary);
        staffAccount.setPerson(person);
        // Save staff account to database
        staffAccountRepository.save(staffAccount);
        Long staffId = staffAccount.getAccountID();

        // Create timeslot 1 (open hours)
        DayOfWeek dayOfTheWeek = DayOfWeek.MONDAY;
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(4, 30);
        TimeSlot mondaySlot = new TimeSlot();
        mondaySlot.setDayOfTheWeek(dayOfTheWeek);
        mondaySlot.setStartTime(startTime);
        mondaySlot.setEndTime(endTime);
        mondaySlot.setSystem(parkingSystem);
        // Save timeslot to database
        timeSlotRepository.save(mondaySlot);
        Long timeSlotID = mondaySlot.getTimeSlotID();

        // Create timeslot 2 (staff working hours)
        DayOfWeek dayOfTheWeek2 = DayOfWeek.TUESDAY;
        LocalTime startTime2 = LocalTime.of(9, 0);
        LocalTime endTime2 = LocalTime.of(5, 30);
        TimeSlot tuesdaySlot = new TimeSlot();
        tuesdaySlot.setDayOfTheWeek(dayOfTheWeek2);
        tuesdaySlot.setStartTime(startTime2);
        tuesdaySlot.setEndTime(endTime2);
        tuesdaySlot.setStaffAccount(staffAccount);
        // Save timeslot to database
        timeSlotRepository.save(tuesdaySlot);
        Long timeSlotID2 = tuesdaySlot.getTimeSlotID();

        // Read object and assert attributes
        mondaySlot = null;
        tuesdaySlot = null;
        parkingSystem = null;
        staffAccount = null;
        person = null;

        // TimeSlot 1
        mondaySlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        assertNotNull(mondaySlot);
        assertEquals(timeSlotID, mondaySlot.getTimeSlotID());
        assertEquals(dayOfTheWeek, mondaySlot.getDayOfTheWeek());
        assertEquals(startTime, mondaySlot.getStartTime());
        assertEquals(endTime, mondaySlot.getEndTime());
        assertNotNull(mondaySlot.getSystem());
        assertEquals(parkingLotSoftwareSystemID, mondaySlot.getSystem().getParkingLotSoftwareSystemID());
        assertNull(mondaySlot.getStaffAccount());

        // TimeSlot 2
        tuesdaySlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID2);
        assertNotNull(tuesdaySlot);
        assertEquals(timeSlotID2, tuesdaySlot.getTimeSlotID());
        assertEquals(dayOfTheWeek2, tuesdaySlot.getDayOfTheWeek());
        assertEquals(startTime2, tuesdaySlot.getStartTime());
        assertEquals(endTime2, tuesdaySlot.getEndTime());
        assertNull(tuesdaySlot.getSystem());
        assertNotNull(tuesdaySlot.getStaffAccount());
        assertEquals(staffId, tuesdaySlot.getStaffAccount().getAccountID());
    }

    @Test
    public void testFindByStaffAccountID() {
        // System
        float monthlyFee = 5;
        float feePer15m = 5;
        int maxStay = 5;
        int numberOfRegularParkingSpots = 500;
        int numberOfLargeParkingSpots = 50;
        int numberOfMonthlyFloors = 5;
        int numberOfMonthlySpotsPerFloor = 500;
        int numberOfGarages = 5;
        ParkingLotSoftwareSystem system = new ParkingLotSoftwareSystem();
        system.setMonthlyFee(monthlyFee);
        system.setFeePer15m(feePer15m);
        system.setMaxStay(maxStay);
        system.setNumberOfRegularParkingSpots(numberOfRegularParkingSpots);
        system.setNumberOfLargeParkingSpots(numberOfLargeParkingSpots);
        system.setNumberOfMonthlyFloors(numberOfMonthlyFloors);
        system.setNumberOfMonthlySpotsPerFloor(numberOfMonthlySpotsPerFloor);
        system.setNumberOfGarages(numberOfGarages);
        parkingLotSoftwareSystemRepository.save(system);
        // Long parkingLotSoftwareSystemID = system.getParkingLotSoftwareSystemID();

        // Staff 1
        Person person = new Person("32103217", "Bob");
        String email = "whats@ligma.com";
        String password = "gottem";
        float salary = 11.1f;
        StaffAccount staffAccountBob = new StaffAccount(email, password, person, salary);
        staffAccountRepository.save(staffAccountBob);
        Long BobId = staffAccountBob.getAccountID();

        // Staff 2
        Person person2 = new Person("33213117", "Jack");
        String email2 = "imagine@ligma.com";
        String password2 = "gottem2";
        float salary2 = 11.2f;
        StaffAccount staffAccountJack = new StaffAccount(email2, password2, person2, salary2);
        staffAccountRepository.save(staffAccountJack);
        Long JackId = staffAccountJack.getAccountID();

        // TimeSlot 1 (Bob)
        TimeSlot timeSlotBob1 = new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotBob1.setStaffAccount(staffAccountBob);
        timeSlotRepository.save(timeSlotBob1);
        Long timeSlotBob1Id = timeSlotBob1.getTimeSlotID();

        // TimeSlot 2 (Bob)
        TimeSlot timeSlotBob2 = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotBob2.setStaffAccount(staffAccountBob);
        timeSlotRepository.save(timeSlotBob2);
        Long timeSlotBob2Id = timeSlotBob2.getTimeSlotID();

        // TimeSlot 3 (Jack)
        TimeSlot timeSlotJack1 = new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotJack1.setStaffAccount(staffAccountJack);
        timeSlotRepository.save(timeSlotJack1);
        Long timeSlotJack1Id = timeSlotJack1.getTimeSlotID();

        // TimeSlot 4 (Open hours)
        TimeSlot timeSlotOpenHours = new TimeSlot(DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotOpenHours.setSystem(system);
        timeSlotRepository.save(timeSlotOpenHours);

        List<TimeSlot> timeSlotsBob = timeSlotRepository.findTimeSlotByStaffAccountAccountID(BobId);
        assertEquals(2, timeSlotsBob.size());
        assertEquals(timeSlotBob1Id, timeSlotsBob.get(0).getTimeSlotID());
        assertEquals(BobId, timeSlotsBob.get(0).getStaffAccount().getAccountID());
        assertEquals(DayOfWeek.MONDAY, timeSlotsBob.get(0).getDayOfTheWeek());
        assertNull(timeSlotsBob.get(0).getSystem());
        assertEquals(timeSlotBob2Id, timeSlotsBob.get(1).getTimeSlotID());
        assertEquals(BobId, timeSlotsBob.get(1).getStaffAccount().getAccountID());
        assertNull(timeSlotsBob.get(1).getSystem());
        List<TimeSlot> timeSlotsJack = timeSlotRepository.findTimeSlotByStaffAccountAccountID(JackId);
        assertEquals(1, timeSlotsJack.size());
        assertEquals(timeSlotJack1Id, timeSlotsJack.get(0).getTimeSlotID());
        assertEquals(JackId, timeSlotsJack.get(0).getStaffAccount().getAccountID());
        assertNull(timeSlotsJack.get(0).getSystem());
    }

    @Test
    public void testFindbyStaffAccount() {
        // System
        float monthlyFee = 5;
        float feePer15m = 5;
        int maxStay = 5;
        int numberOfRegularParkingSpots = 500;
        int numberOfLargeParkingSpots = 50;
        int numberOfMonthlyFloors = 5;
        int numberOfMonthlySpotsPerFloor = 500;
        int numberOfGarages = 5;
        ParkingLotSoftwareSystem system = new ParkingLotSoftwareSystem();
        system.setMonthlyFee(monthlyFee);
        system.setFeePer15m(feePer15m);
        system.setMaxStay(maxStay);
        system.setNumberOfRegularParkingSpots(numberOfRegularParkingSpots);
        system.setNumberOfLargeParkingSpots(numberOfLargeParkingSpots);
        system.setNumberOfMonthlyFloors(numberOfMonthlyFloors);
        system.setNumberOfMonthlySpotsPerFloor(numberOfMonthlySpotsPerFloor);
        system.setNumberOfGarages(numberOfGarages);
        parkingLotSoftwareSystemRepository.save(system);
        Long parkingLotSoftwareSystemID = system.getParkingLotSoftwareSystemID();

        // Staff 1
        Person person = new Person("32103217", "Bob");
        String email = "whats@ligma.com";
        String password = "gottem";
        float salary = 11.1f;
        StaffAccount staffAccountBob = new StaffAccount(email, password, person, salary);
        staffAccountRepository.save(staffAccountBob);

        // TimeSlot 1 (Bob)
        TimeSlot timeSlotBob1 = new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotBob1.setStaffAccount(staffAccountBob);
        timeSlotRepository.save(timeSlotBob1);

        // TimeSlot 2 (Open hours)
        TimeSlot timeSlotOpenHoursTUE = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotOpenHoursTUE.setSystem(system);
        timeSlotRepository.save(timeSlotOpenHoursTUE);
        Long timeSlotOpenHoursTUEId = timeSlotOpenHoursTUE.getTimeSlotID();

        // TimeSlot 3 (Open hours)
        TimeSlot timeSlotOpenHoursWED = new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotOpenHoursWED.setSystem(system);
        timeSlotRepository.save(timeSlotOpenHoursWED);
        Long timeSlotOpenHoursWEDId = timeSlotOpenHoursWED.getTimeSlotID();

        // TimeSlot 4 (Open hours)
        TimeSlot timeSlotOpenHoursTHU = new TimeSlot(DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(4, 30));
        timeSlotOpenHoursTHU.setSystem(system);
        timeSlotRepository.save(timeSlotOpenHoursTHU);
        Long timeSlotOpenHoursTHUId = timeSlotOpenHoursTHU.getTimeSlotID();

        List<TimeSlot> timeSlotsOpenHours = timeSlotRepository.findTimeSlotByStaffAccount(null);
        assertEquals(3, timeSlotsOpenHours.size());
        assertEquals(timeSlotOpenHoursTUEId, timeSlotsOpenHours.get(0).getTimeSlotID());
        assertEquals(DayOfWeek.TUESDAY, timeSlotsOpenHours.get(0).getDayOfTheWeek());
        assertEquals(parkingLotSoftwareSystemID, timeSlotsOpenHours.get(0).getSystem().getParkingLotSoftwareSystemID());
        assertNull(timeSlotsOpenHours.get(0).getStaffAccount());
        assertEquals(timeSlotOpenHoursWEDId, timeSlotsOpenHours.get(1).getTimeSlotID());
        assertEquals(parkingLotSoftwareSystemID, timeSlotsOpenHours.get(1).getSystem().getParkingLotSoftwareSystemID());
        assertNull(timeSlotsOpenHours.get(1).getStaffAccount());
        assertEquals(timeSlotOpenHoursTHUId, timeSlotsOpenHours.get(2).getTimeSlotID());
        assertEquals(parkingLotSoftwareSystemID, timeSlotsOpenHours.get(2).getSystem().getParkingLotSoftwareSystemID());
        assertNull(timeSlotsOpenHours.get(2).getStaffAccount());
    }

}
