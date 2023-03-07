package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.data.PersonMockBuilder;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MonthlyReservationRepositoryTest {
    @Autowired MonthlyReservationRepository monthlyReservationRepository;

    @Autowired PersonRepository personRepository;

    // clear database after testing
    @AfterEach
    void setup() {
        monthlyReservationRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadMonthlyReservation() {
        // Create a MonthlyReservation
        Person person = new PersonMockBuilder().build();
        MonthlyReservation monthlyReservation = new MonthlyReservation(LocalDate.now(), LocalDate.now().plusDays(30), person);

        // Save the MonthlyReservation
        Person savedPerson = personRepository.save(person);
        MonthlyReservation savedMonthlyReservation = monthlyReservationRepository.save(monthlyReservation);

        // Check that the MonthlyReservation was saved correctly
        assertNotNull(savedMonthlyReservation);
        assertEquals(monthlyReservation.getMonthlyReservationID(), savedMonthlyReservation.getMonthlyReservationID());
        assertEquals(monthlyReservation.getStartDate(), savedMonthlyReservation.getStartDate());
        assertEquals(monthlyReservation.getEndDate(), savedMonthlyReservation.getEndDate());
        assertEquals(monthlyReservation.getPerson(), savedMonthlyReservation.getPerson());

        // Retrieve the MonthlyReservation
        MonthlyReservation retrievedMonthlyReservation = monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(savedMonthlyReservation.getMonthlyReservationID());
        
        // Check that the MonthlyReservation was retrieved correctly
        assertNotNull(retrievedMonthlyReservation);
        assertEquals(savedMonthlyReservation.getMonthlyReservationID(), retrievedMonthlyReservation.getMonthlyReservationID());
        assertEquals(savedMonthlyReservation.getStartDate(), retrievedMonthlyReservation.getStartDate());
        assertEquals(savedMonthlyReservation.getEndDate(), retrievedMonthlyReservation.getEndDate());
        assertEquals(savedMonthlyReservation.getPerson(), retrievedMonthlyReservation.getPerson());
    }

    @Test
    void testGetAllMonthlyReservationsOfPerson() {
        // Create a Person
        Person person = new PersonMockBuilder().build();

        // Create 3 MonthlyReservations for the Person
        MonthlyReservation monthlyReservation = new MonthlyReservation(LocalDate.now(), LocalDate.now().plusDays(30), person);
        MonthlyReservation monthlyReservation2 = new MonthlyReservation(LocalDate.now().plusDays(30), LocalDate.now().plusDays(60), person);
        MonthlyReservation monthlyReservation3 = new MonthlyReservation(LocalDate.now().plusDays(60), LocalDate.now().plusDays(90), person);

        // Save the Person and the MonthlyReservations
        Person savedPerson = personRepository.save(person);
        MonthlyReservation savedMonthlyReservation = monthlyReservationRepository.save(monthlyReservation);
        MonthlyReservation savedMonthlyReservation2 = monthlyReservationRepository.save(monthlyReservation2);
        MonthlyReservation savedMonthlyReservation3 = monthlyReservationRepository.save(monthlyReservation3);

        // Retrieve the MonthlyReservations of the person
        List<MonthlyReservation> monthlyReservations = monthlyReservationRepository.findMonthlyReservationByPerson_PersonID(savedPerson.getPersonID());

        // Check that the MonthlyReservations were retrieved correctly
        assertNotNull(monthlyReservations);
        assertEquals(3, monthlyReservations.size());
        
        assertEquals(savedMonthlyReservation.getMonthlyReservationID(), monthlyReservations.get(0).getMonthlyReservationID());
        assertEquals(savedMonthlyReservation.getStartDate(), monthlyReservations.get(0).getStartDate());
        assertEquals(savedMonthlyReservation.getEndDate(), monthlyReservations.get(0).getEndDate());
        assertEquals(savedMonthlyReservation.getPerson(), monthlyReservations.get(0).getPerson());

        assertEquals(savedMonthlyReservation2.getMonthlyReservationID(), monthlyReservations.get(1).getMonthlyReservationID());
        assertEquals(savedMonthlyReservation2.getStartDate(), monthlyReservations.get(1).getStartDate());
        assertEquals(savedMonthlyReservation2.getEndDate(), monthlyReservations.get(1).getEndDate());
        assertEquals(savedMonthlyReservation2.getPerson(), monthlyReservations.get(1).getPerson());

        assertEquals(savedMonthlyReservation3.getMonthlyReservationID(), monthlyReservations.get(2).getMonthlyReservationID());
        assertEquals(savedMonthlyReservation3.getStartDate(), monthlyReservations.get(2).getStartDate());
        assertEquals(savedMonthlyReservation3.getEndDate(), monthlyReservations.get(2).getEndDate());
        assertEquals(savedMonthlyReservation3.getPerson(), monthlyReservations.get(2).getPerson());
    }
}
