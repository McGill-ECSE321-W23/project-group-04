package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MonthlyReservationRepositoryTest {
    @Autowired MonthlyReservationRepository monthlyReservationRepository;

    @AfterEach
    void setup() {
        monthlyReservationRepository.deleteAll();
    }
/*
    @Test
    void testPersistAndLoadMonthlyReservation() {
        Person person = new Person();
        person.setName("Jon");
        person.setPhoneNumber("5554443333");
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setFloor(2);
        parkingSpot.setNumber(3);
        MonthlyReservation monthlyReservation = new MonthlyReservation(LocalDate.now(), LocalDate.now().plusDays(1), parkingSpot);

        Long savedId = monthlyReservationRepository.save(monthlyReservation).getMonthlyReservationID();
        MonthlyReservation retrievedAccount = monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(savedId);

        assertNotNull(retrievedAccount);
        assertEquals(savedId, monthlyReservation.getMonthlyReservationID());
    }
*/
}
