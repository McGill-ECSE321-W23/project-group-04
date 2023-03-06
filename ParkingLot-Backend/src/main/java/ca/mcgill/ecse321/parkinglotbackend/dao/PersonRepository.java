package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    boolean existsPersonByPersonID(Long personId);
    Person findPersonByPersonID(Long personId);
    boolean existsPersonByName(String name);
    Person findPersonByName(String name);
    boolean existsPersonByPhoneNumber(String phoneNumber);
    Person findPersonByPhoneNumber(String phoneNumber);
}
