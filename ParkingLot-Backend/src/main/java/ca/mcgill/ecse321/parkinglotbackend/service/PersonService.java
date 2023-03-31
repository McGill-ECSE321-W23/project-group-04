package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

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
     * @throws Exception
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
     * @return List<Person>
     * @author Lin Wei Li
     */
    @Transactional
    public List<Person> getAllPersons() {
        return toList(personRepository.findAll());
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
        // Check for duplicate phone number
        if (personRepository.existsPersonByPhoneNumber(phoneNumber)) {
            throw new Exception("Phone number already exists!");
        }

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
    public Person deletePerson(long personID) throws Exception {
        Person person = personRepository.findPersonByPersonID(personID);
        if (person == null) {
            throw new Exception("No person with this id exists!");
        }
        personRepository.delete(person);
        return person;
    }

    /**
     * Update a person
     * @param personID
     * @param name - new name
     * @param phoneNumber - new phone number
     * @return Person
     * @throws Exception
     * @author Lin Wei Li
     */
    @Transactional
    public Person updatePerson(long personID, String name, String phoneNumber) throws Exception {
        // Check for duplicate phone number
        Person dupePhoneNumberPerson = personRepository.findPersonByPhoneNumber(phoneNumber);
        if (dupePhoneNumberPerson != null && dupePhoneNumberPerson.getPersonID() != personID) {
            throw new Exception("Phone number already exists!");
        }

        Person person = personRepository.findPersonByPersonID(personID);

        // Check input
        if (person == null) {
            throw new Exception("No person with this id exists!");
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

    /**
     * Helper method to convert an Iterable to a List
     * @param iterable
     * @return List<T>
     * @author Lin Wei Li
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
