package ca.mcgill.ecse321.parkinglotbackend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.parkinglotbackend.data.PersonMockBuilder;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

public class PersonRepositoryTest {
    @Autowired PersonRepository personRepository;

    @AfterEach
    void setup() {
        personRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadPerson() {
        // Create a person
        Person person = new PersonMockBuilder().build();

        // Save the person
        Person savedPerson = personRepository.save(person);

        // Check that the person was saved correctly
        assertNotNull(savedPerson);
        assertEquals(person.getPersonID(), savedPerson.getPersonID());
        assertEquals(person.getName(), savedPerson.getName());
        assertEquals(person.getPhoneNumber(), savedPerson.getPhoneNumber());

        // Retrieve the person
        Person retrievedPerson = personRepository.findPersonByPersonID(savedPerson.getPersonID());

        // Check that the person was retrieved correctly
        assertNotNull(retrievedPerson);
        assertEquals(savedPerson.getPersonID(), retrievedPerson.getPersonID());
        assertEquals(savedPerson.getName(), retrievedPerson.getName());
        assertEquals(savedPerson.getPhoneNumber(), retrievedPerson.getPhoneNumber());
    }
}
