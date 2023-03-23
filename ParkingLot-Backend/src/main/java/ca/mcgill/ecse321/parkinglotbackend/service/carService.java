package ca.mcgill.ecse321.parkinglotbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.CarRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;


@Service
public class carService {

    @Autowired
    CarRepository carRepository;
    @Autowired
    PersonRepository personRepository;

    @Transactional
    public Person createPerson(){

    }


    
}
