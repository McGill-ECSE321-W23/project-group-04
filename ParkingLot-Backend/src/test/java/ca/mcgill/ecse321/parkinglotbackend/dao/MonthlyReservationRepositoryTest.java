package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MonthlyReservationRepositoryTest {
    @Autowired MonthlyReservationRepository monthlyReservationRepository;

    @BeforeEach
    void setup() {
        monthlyReservationRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadMonthlyReservation() {
        String reservationId = "123";
        Person person = new Person("11", "5554443333", "Jon");
        ParkingSpot parkingSpot = new ParkingSpot("parkingId", 2, 1);
        MonthlyReservation monthlyReservation = new MonthlyReservation(reservationId, LocalDate.MIN, LocalDate.MAX, parkingSpot, person);

        monthlyReservationRepository.save(monthlyReservation);
        MonthlyReservation retrievedAccount = monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(reservationId);

        assertNotNull(retrievedAccount);
        assertEquals(reservationId, monthlyReservation.getMonthlyReservationID());
    }
}
