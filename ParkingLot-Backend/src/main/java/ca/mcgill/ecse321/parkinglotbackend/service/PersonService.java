package ca.mcgill.ecse321.parkinglotbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    /**
     * Find a person by id
     * @param id
     * @return Person
     * @author Lin Wei Li
     */
    @Transactional
    public Person getPersonByID(long id) throws Exception {
        Person person = personRepository.findPersonByPersonID(id);
        if (person == null) {
            throw new Exception("No person with this id exists!");
        }
        return person;
    }

    /**
     * Find a person by name
     * @param name
     * @return Person
     * @author Lin Wei Li
     */
    @Transactional
    public Person getPersonByName(String name) {
        return personRepository.findPersonByName(name);
    }

    /**
     * Find a person by phone number
     * @param phoneNumber
     * @return Person
     * @author Lin Wei Li
     */
    @Transactional
    public Person getPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findPersonByPhoneNumber(phoneNumber);
    }

    /**
     * Get all persons
     * @param name
     * @param phoneNumber
     * @return Iterable<Person>
     * @author Lin Wei Li
     */
    @Transactional
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Create a person
     * @param name
     * @param phoneNumber
     * @return Person
     * @author Lin Wei Li
     */
    @Transactional
    public Person createPerson(String name, String phoneNumber) throws Exception {

        // Check input
        if (name == null || name.trim().length() == 0) {
            throw new Exception("Name cannot be empty!");
        }
        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            throw new Exception("Phone number cannot be empty!");
        }

        Person person = new Person(phoneNumber, name);
        personRepository.save(person);
        return person;
    }

    /**
     * Delete a person
     * @param personID
     * @return Person
     * @author Lin Wei Li
     */
    @Transactional
    public Person deletePerson(long personID) {
        Person person = personRepository.findPersonByPersonID(personID);
        personRepository.delete(person);
        return person;
    }

    /**
     * Update a person
     * @param personID
     * @param name
     * @param phoneNumber
     * @return Person
     * @author Lin Wei Li
     */
    @Transactional
    public Person updatePerson(long personID, String name, String phoneNumber) throws Exception {
        Person person = personRepository.findPersonByPersonID(personID);

        // Check input
        if (person == null) {
            throw new Exception("Person does not exist!");
        }
        if (name == null || name.trim().length() == 0) {
            throw new Exception("Name cannot be empty!");
        }
        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            throw new Exception("Phone number cannot be empty!");
        }

        person.setName(name);
        person.setPhoneNumber(phoneNumber);
        personRepository.save(person);
        return person;
    }
    
}
