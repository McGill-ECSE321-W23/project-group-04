package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.controller.CarRestController;
import ca.mcgill.ecse321.parkinglotbackend.dao.CarRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

public class CarService {

    @Autowired
    CarRepository carRepository;
    @Autowired
    PersonRepository personRepository;

    
    @Transactional
    public Person createPerson(String name, String phoneNumber){
        Person person = new Person();
        person.setName(name);
        person.setPhoneNumber(phoneNumber);
        personRepository.save(person);
        return person;
    }

    @Transactional
    public Person findPersonByID(Long id) throws Exception{
        Person person = personRepository.findPersonByPersonID(id);
        if (person == null){
            throw new Exception("No person with this ID exists");
        }
        return person;
    }

    @Transactional 
    public Car createCar(String name, String phoneNumber, String licensePlate, String make, String model){
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setMake(make);
        car.setModel(model);
        car.setOwner(createPerson(name, phoneNumber));
        carRepository.save(car);
        return car;
    }

    @Transactional
    public Car registerCar(Person person, String licensePlate, String make, String model){
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setMake(make);
        car.setModel(model);
        car.setOwner(person);
        carRepository.save(car);
        return car;
    }

    @Transactional
    public Car getCarByID (long id) throws Exception{
        Car car = carRepository.findCarByCarID(id);
        if (car == null){
            throw new Exception("No car with this ID exists");
        }
        return car;
    }

    @Transactional
    public Car getCarByLicensePlate (String licensePlate) throws Exception{
        Car car = carRepository.findCarByLicensePlate(licensePlate);
        if (car == null){
            throw new Exception("No car with this license plate exists");
        }
        return car;
    }

    @Transactional
    public List<Car> findCarByOwnerID(Long id){
        List<Car> carsOfPerson = carRepository.findCarByOwner_PersonID(id);
        return carsOfPerson;
    }


    @Transactional
    public List<Car> getAllCars() {
        return toList(carRepository.findAll());
    }


    @Transactional
    public Car deleteCar(String licensePlate) {
        Car car = carRepository.findCarByLicensePlate(licensePlate);
        carRepository.delete(car);
        return car;
    }

    @Transactional
    public Car updateCar(long carId, String licensePlate, String make, String model, Person person) throws Exception {
        
        Car car = getCarByID(carId);
        
        if (person == null) {
            throw new Exception("A car requires an owner.");
        }
        if (licensePlate == null || licensePlate.trim().length() == 0) {
            throw new Exception("License Plate cannot be empty!");
        }

        car.setLicensePlate(licensePlate);
        car.setMake(make);
        car.setModel(model);
        car.setOwner(person);
        carRepository.save(car);

        return car;
    }


    /**
     * Taken from 321 tutorial 
     * Takes an 
     * 
     * @param <T>
     * @param iterable
     * @return
     */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
    }


}